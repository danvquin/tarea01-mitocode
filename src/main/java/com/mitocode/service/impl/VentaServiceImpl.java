package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Venta;
import com.mitocode.repo.IVentaRepo;
import com.mitocode.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService{

	@Autowired
	private IVentaRepo repo;

	@Override
	public Venta registrar(Venta ven) {
		ven.getDetalleVenta().forEach(det -> det.setVenta(ven));
		return repo.save(ven);
	}

	@Override
	public Venta modificar(Venta ven) {
		return repo.save(ven);
				
	}

	@Override
	public Venta leer(Integer idVenta) {
		return repo.findOne(idVenta);
	}

	@Override
	public List<Venta> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idVenta) {
		repo.delete(idVenta);
		
	}

	
	
}

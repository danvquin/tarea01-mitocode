package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Producto;
import com.mitocode.repo.IProductoRepo;
import com.mitocode.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoRepo repo;

	@Override
	public Producto registrar(Producto pro) {
		return repo.save(pro);
	}

	@Override
	public Producto modificar(Producto pro) {
		return repo.save(pro);
				
	}

	@Override
	public Producto leer(Integer idProducto) {
		return repo.findOne(idProducto);
	}

	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idProducto) {
		repo.delete(idProducto);
		
	}

	
	
}

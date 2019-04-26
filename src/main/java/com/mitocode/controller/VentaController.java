package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Venta;
import com.mitocode.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;
	
	
	@GetMapping
	public ResponseEntity<List<Venta>> mostrar() {
		List<Venta> ventas = service.listar();
		return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> listarPorId(@PathVariable("id") Integer idVenta) {
		Venta ven = service.leer(idVenta);
		if(ven == null) {
			throw new ModeloNotFoundException("Id no encontrado: "+idVenta);
		} else {
			return new ResponseEntity<Venta>(ven, HttpStatus.OK);	
		}
	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Venta ven) {
		Venta ventas = service.registrar(ven);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ventas.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Venta ven) {
		service.modificar(ven);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idVenta) {
		Venta ven = service.leer(idVenta);
		if(ven == null) {
			throw new ModeloNotFoundException("Id no encontrado: "+idVenta);
		} else {
			service.eliminar(idVenta);	
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
		
	}
}

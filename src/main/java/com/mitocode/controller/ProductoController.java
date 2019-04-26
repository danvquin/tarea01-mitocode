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
import com.mitocode.model.Producto;
import com.mitocode.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Producto>> mostrar() {
		List<Producto> productos = service.listar();
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer idProducto) {
		Producto pro = service.leer(idProducto);
		if(pro == null) {
			throw new ModeloNotFoundException("Id no encontrado: "+idProducto);
		} else {
			return new ResponseEntity<Producto>(pro, HttpStatus.OK);	
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto pro) {
		Producto productos = service.registrar(pro);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productos.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Producto pro) {
		service.modificar(pro);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idProducto) {
		Producto pro = service.leer(idProducto);
		if(pro == null) {
			throw new ModeloNotFoundException("Id no encontrado: "+idProducto);
		} else {
			service.eliminar(idProducto);	
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
		
	}
}


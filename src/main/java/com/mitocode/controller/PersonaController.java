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
import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService service;
	
	
	@GetMapping
	public ResponseEntity<List<Persona>> mostrar() {
		List<Persona> personas = service.listar();
		return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer idPersona) {
		Persona per = service.leer(idPersona);
		if(per == null) {
			throw new ModeloNotFoundException("Id no encontrado: "+idPersona);
		} else {
			return new ResponseEntity<Persona>(per, HttpStatus.OK);	
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Persona per) {
		Persona persona = service.registrar(per);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(persona.getIdPersona()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Persona per) {
		service.modificar(per);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idPersona) {
		Persona per = service.leer(idPersona);
		if(per == null) {
			throw new ModeloNotFoundException("Id no encontrado: "+idPersona);
		} else {
			service.eliminar(idPersona);	
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
		
	}
}

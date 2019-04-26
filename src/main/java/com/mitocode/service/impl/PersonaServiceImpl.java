package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Persona;
import com.mitocode.repo.IPersonaRepo;
import com.mitocode.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService{

	@Autowired
	private IPersonaRepo repo;

	@Override
	public Persona registrar(Persona per) {
		return repo.save(per);
	}

	@Override
	public Persona modificar(Persona per) {
		return repo.save(per);
				
	}

	@Override
	public Persona leer(Integer idPersona) {
		return repo.findOne(idPersona);
	}

	@Override
	public List<Persona> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer idPersona) {
		repo.delete(idPersona);
		
	}

	
	
}

package com.ricardo.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ricardo.cursomc.domain.Endereco;
import com.ricardo.cursomc.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Endereco obj = service.buscar(id);

		return ResponseEntity.ok().body(obj);
	}

}

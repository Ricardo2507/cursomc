package com.ricardo.cursomc.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ricardo.cursomc.domain.Categoria;
import com.ricardo.cursomc.services.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		
		  Categoria obj = service.buscar(id);
		/*
		 * 
		 * Categoria cat1 = new Categoria(1, "Informática"); Categoria cat2 = new
		 * Categoria(2, "Escritório");
		 * 
		 * List<Categoria> lista = new ArrayList<>(); lista.add(cat1); lista.add(cat2);
		 */
		 
		
		return ResponseEntity.ok().body(obj);
	}

}

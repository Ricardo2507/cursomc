package com.ricardo.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardo.cursomc.domain.Categoria;
import com.ricardo.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	// inserindo uma nova categoria

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {

		obj = service.insert(obj);
		// boa prática - tratar os códigos de resposta -201 - A requisição foi bem
		// sucedida e
		// um novo recurso foi criado como resultado. Esta é uma tipica resposta enviada
		// após uma requisição POST.
		// o método fromCurrentRequestUri() pega a URL que usamos para inserir
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	// atualizando uma categoria
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id); 
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
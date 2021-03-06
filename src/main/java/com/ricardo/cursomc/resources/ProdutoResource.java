package com.ricardo.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.cursomc.domain.Produto;
import com.ricardo.cursomc.dto.ProdutoDTO;
import com.ricardo.cursomc.resources.utils.URL;
import com.ricardo.cursomc.services.ProdutoService;


//Anotação do controlador rest
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	// Associando a função ao Rest com método de get.
	// O value é o id que será informado na hora de buscar alguma informação
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// O Tipo ResponseEntity é a resposta da busca
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	// Vai retornar as categorias de acordo com página
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			// Usa parâmetros opcionais, primeiro definimos qual é a variável que vai
			// receber o valor e depois passamos um valor padrão
			// parâmetro de url é String
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categoria", defaultValue = "") String categoria,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		String nomeLimpo = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeURL(categoria);

		// FindAll retorna uma LISTA de produtos
		Page<Produto> objs = service.search(nomeLimpo, ids, page, linesPerPage, orderBy, direction);

		// Faz uma lista secundaria, que vai "mapear" cada item da lista objs seguindo
		// os valores estabelecidos como parâmetro
		// será criado uma categoriaDTO para cada objeto da Page
		Page<ProdutoDTO> objDTOs = objs.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(objDTOs);
	}
}

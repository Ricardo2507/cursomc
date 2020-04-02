package com.ricardo.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.cursomc.domain.Cidade;
import com.ricardo.cursomc.domain.Estado;
import com.ricardo.cursomc.dto.CidadeDTO;
import com.ricardo.cursomc.dto.EstadoDTO;
import com.ricardo.cursomc.services.CidadeService;
import com.ricardo.cursomc.services.EstadoService;


@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> estados = estadoService.findAll();
		List<EstadoDTO> listDto = estados.stream().map(obj -> new EstadoDTO(obj)).collect((Collectors.toList()));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	//Buscar cidades
	@RequestMapping(value="/{estado_id}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estado_id){
		List<Cidade> objs = cidadeService.findByEstado(estado_id);
		List<CidadeDTO> objDTOs = objs.stream().map(objeto -> new CidadeDTO(objeto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTOs);
	}
}
package com.ricardo.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.cursomc.domain.Estado;
import com.ricardo.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository estadoRepository;
	
	//Buscar todos os estados
	public List<Estado> findAll(){
		return estadoRepository.findAllByOrderByNome();
	}
}

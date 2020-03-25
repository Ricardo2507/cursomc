package com.ricardo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ricardo.cursomc.domain.Endereco;
import com.ricardo.cursomc.repositories.EnderecoRepository;
import com.ricardo.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;
	
	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
		}

}

package com.ricardo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.cursomc.domain.Categoria;
import com.ricardo.cursomc.repositories.CategoriaRepository;
import com.ricardo.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}

	// inserindo uma nova categoria
	public Categoria insert(Categoria obj) {
		// para garantir que é um novo objeto. Tem que ter id nulo.Dessa se o id tiver alguma coisa
		// o save irá considerar como uma atualização
		obj.setId(null);
				
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());  // verificar se categoria existe
		return repo.save(obj);
	}

}

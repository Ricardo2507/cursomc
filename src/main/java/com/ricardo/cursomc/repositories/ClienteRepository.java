package com.ricardo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	//Com base no nome da variavel email ele faz uma busca no banco de dados.
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);

}

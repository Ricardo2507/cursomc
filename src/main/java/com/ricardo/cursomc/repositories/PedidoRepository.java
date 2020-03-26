package com.ricardo.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.cursomc.domain.Cliente;
import com.ricardo.cursomc.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	// Essa interface com a anotação @Repository, permite realizar buscas no banco
	// de dados, ela estende do JpaRepository e informa
	// Qual o tipo da classe/objeto que será buscado e qual é o ID desse objeto,
	// neste caso nós definimos como Integer.

	@Transactional(readOnly = true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pegable);
}

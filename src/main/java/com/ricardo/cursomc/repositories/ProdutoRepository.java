package com.ricardo.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.cursomc.domain.Categoria;
import com.ricardo.cursomc.domain.Produto;

//Essa interface com a anotação @Repository, permite realizar buscas no banco de dados, ela estende do JpaRepository e informa
//Qual o tipo da classe/objeto que será buscado e qual é o ID desse objeto, neste caso nós definimos como Integer. 
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	// Faz uma busca com o JPQL para retornar uma página de produtos
	// cat é o nome de referencia do obj.categoria
    // a query se sobrepõe à consulta do spring data
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")

	// Aqui eu informo que o parâmetro nome vai receber o conteúdo da string nome e
	// o parâmetro categorias vai receber o conteúdo da lista de categorias

	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome,
			@Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	/*
	 * Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,
	 * List<Categoria> categorias, Pageable pageRequest);
	 */

}

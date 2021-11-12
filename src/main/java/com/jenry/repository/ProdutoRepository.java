package com.jenry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jenry.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("select p from Produto p where p.saborCobertura like %?1%")
	List<Produto> findProdutoBySabor(String sabor);

}

package com.donutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.donutec.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNomeContaining(String nome);
	
	@Query("select c from Cliente c where c.nome like %?1%")
	List<Cliente> findClienteByName(String nome);
}

package com.donutec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.donutec.model.Cliente;

public interface ClienteServiceApi {
	
	Page<Cliente> getAll (Pageable pageable);
}

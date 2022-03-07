package com.donutec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.donutec.model.Cliente;
import com.donutec.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteServiceApi {
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Override
	public Page<Cliente> getAll(Pageable pageable) {
		
		return clienteRepo.findAll(pageable);
	}

}

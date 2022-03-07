package com.donutec.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donutec.model.Cliente;
import com.donutec.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepo;
/*
  public Cliente salvarCliente(Cliente cliente, Model model,RedirectAttributes ra ) {
	  
	  if (cliente.getDataNascimento()==null) {
			clienteRepo.save(cliente);
		} else {
			if(cliente.getDataNascimento().isAfter(LocalDate.now())) {
				cliente.setDataNascimento(ra);
				model.addAttribute("mensagem", "Informe uma data válida para nascimento.");
				 return cliente;
			}
		}	 
	  
	  
	  
	  return cliente;
  }*/
}

package com.donutec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.donutec.model.Cliente;
import com.donutec.repository.ClienteRepository;

@Controller
@RequestMapping("venda")
public class VendaController {
	
	List<Cliente> clientes = new ArrayList<>();
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@RequestMapping("ponto_venda")
	public String abrirPontoVenda(Model model) {
		return "venda/ponto_venda";
	}
	
	@RequestMapping("lista_venda")
	public String abrirListaVenda(Model model) {
		return "venda/lista_venda";
	}
	
	@RequestMapping("finalizar_venda")
	public String abrirFimVenda(Model model) {
		return "venda/finalizar_venda";
	}
	
	@PostMapping("**/pesquisarCliente")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		modelAndView.addObject("clientes", clienteRepo.findClienteByName(nomepesquisa));
		modelAndView.addObject("cienteobj", new Cliente());
		clientes = clienteRepo.findByNomeContaining(nomepesquisa);
		
		return modelAndView;
	}
	
}

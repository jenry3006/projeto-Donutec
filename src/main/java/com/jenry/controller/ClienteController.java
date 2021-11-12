package com.jenry.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jenry.model.Cliente;
import com.jenry.repository.ClienteRepository;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	List<Cliente> clientes = new ArrayList<>();
	
	@Autowired
	ClienteRepository clienteRepo;

	@RequestMapping("cadastro")
	public String abrirCadastro(Cliente cliente, Model model) {
		return"cliente/cadastro";
	}

	
	@RequestMapping("lista")
	public String lista(Cliente cliente, Model model) {
		clientes = clienteRepo.findAll();
 		model.addAttribute("clientes", clientes);
		return"cliente/lista";
	}
	
	@PostMapping("salvar")
	public String salvar(@Valid Cliente cliente, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("erros", bindingResult.getAllErrors());
			return abrirCadastro(cliente, model);
		}		
		clienteRepo.save(cliente);
		return "redirect:/";
	}
	
	@GetMapping(value="editar")
	private String editarCadastro(@PathParam(value="id") Long id,Model model) {
		model.addAttribute("cliente", clienteRepo.getById(id));
		return "cliente/cadastro";
	}
	
	@PostMapping("editar/salvar")
	private String atualizar(@Valid Cliente cliente,BindingResult bindingResult ,Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("erros", bindingResult.getAllErrors());
			return abrirCadastro(cliente, model);
		}
		
		clienteRepo.save(cliente);
		
		return "redirect:/";
	}
	
	/*@GetMapping("/removercliente/{idcliente}")
	private ModelAndView excluir(@PathParam("idcliente")Long idcliente) {
		clienteRepo.deleteById(idcliente);
		ModelAndView modelAndView = new ModelAndView("cliente/cadastro");
		modelAndView.addObject("clientes", clienteRepo.findAll());
		
		return modelAndView;
	}*/
	
	@GetMapping(value="deletar")
	public String deletar(@PathParam(value = "id") Long id, Model model){
		clienteRepo.deleteById(id);
		return "redirect:/cliente/lista";
	}
	
	@PostMapping("**/pesquisarCliente")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		modelAndView.addObject("clientes", clienteRepo.findClienteByName(nomepesquisa));
		modelAndView.addObject("cienteobj", new Cliente());
		
		return modelAndView;
	}
	/*@PostMapping("consultar")
	public String consultar(String consulta, Model model) {
		clientes = clienteRepo.findByNome(consulta);
		clientes = clienteRepo.findByNomeContaining(consulta);
		model.addAttribute("clientes", clientes);
		return"cliente/lista";
	}*/
	
	
	
}	

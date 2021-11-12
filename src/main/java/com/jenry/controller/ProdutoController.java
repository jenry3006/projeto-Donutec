package com.jenry.controller;


import com.jenry.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jenry.repository.ProdutoRepository;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	List<Produto> produtos = new ArrayList<>();

	@Autowired
	ProdutoRepository produtoRepo;
	
	@RequestMapping("cadastro")
	private String abrirCadastro(Produto produto, Model model) {
		return"produto/cadastro";
	}

	@PostMapping("salvar")
	private String salvar(@Valid Produto produto, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			model.addAttribute("erros", bindingResult.getAllErrors());
			return abrirCadastro(produto, model);
		}

		produtoRepo.save(produto);

		return "redirect:/";
	}

	@RequestMapping("lista")
	private String lista(Produto produto, Model model){
		produtos = produtoRepo.findAll();
		model.addAttribute("produtos", produtos);
		return "produto/lista";
	}
	
	@GetMapping("deletar")
	private String deletar(@PathParam (value="id") Long id, Model model) {
		produtoRepo.deleteById(id);
		return"redirect:/produto/lista";
	}
	
	@GetMapping(value="editar")
	private String editarCadastro(@PathParam(value="id") Long id, Model model) {
		model.addAttribute("produto", produtoRepo.getById(id));
		return"produto/cadastro";
	}
	
	@PostMapping("editar/salvar")
	private String atualizar(@Valid Produto produto, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("erros", bindingResult.getAllErrors());
			return abrirCadastro(produto, model);
		}
		
		produtoRepo.save(produto);
		
		return"redirect:/";
	}
	
	@PostMapping("**/pesquisarProduto")
	private ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomePesquisa) {
		ModelAndView modelAndView = new ModelAndView("produto/lista");
		modelAndView.addObject("produtos", produtoRepo.findProdutoBySabor(nomePesquisa));

		return modelAndView;
	}
	
	
}

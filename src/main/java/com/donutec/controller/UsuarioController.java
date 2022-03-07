package com.donutec.controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donutec.model.Usuario;
import com.donutec.repository.UsuarioRepository;
import com.donutec.service.ServiceExc;
import com.donutec.service.UsuarioService;
import com.donutec.util.Util;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @GetMapping("/index")
    public String inicio(Usuario usuario ,HttpSession session) {
    	  //session.setAttribute("usuarioLogado", usuario);
        return "/index";
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.setViewName("login/cadastro");
        return modelAndView;
    }

    @PostMapping("salvarUsuario")
    public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult br, Model model) throws Exception {
    	if(br.hasErrors()) {
    		model.addAttribute("erros", "Verifique os campos");
			return cadastrar();
    	}
    	
    	if(br.hasErrors()) {
    		
    	}
    	
        ModelAndView modelAndView = new ModelAndView();
        usuarioService.salvarUsuario(usuario);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/efetuarLogin")
    public String efetuarLogin(Usuario usuario, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException {

        usuario = this.usuarioRepo.buscarLogin(usuario.getNome(), Util.md5(usuario.getSenha()));

        if (usuario != null){
            session.setAttribute("usuarioLogado", usuario);
            ra.addFlashAttribute("messageok", "Login verificado");
         
            return "redirect:/index";
        } else {
            ra.addFlashAttribute("message", "Login ou senha inválidos");
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}

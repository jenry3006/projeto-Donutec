package com.donutec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donutec.exceptions.CriptoExistException;
import com.donutec.exceptions.EmailExistsException;
import com.donutec.model.Usuario;
import com.donutec.repository.UsuarioRepository;
import com.donutec.util.Util;

import java.security.NoSuchAlgorithmException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public void salvarUsuario(Usuario user) throws Exception {
        try{
            if (usuarioRepo.findByEmail(user.getEmail()) != null){
                throw new EmailExistsException("Já existe um email cadastrado para: " + user.getEmail()  );
               
            }
            user.setSenha(Util.md5(user.getSenha()));

        } catch (NoSuchAlgorithmException e){
            throw new CriptoExistException("Erro na criptografia da senha");
        }
        usuarioRepo.save(user);
    }

    /*public Usuario loginUser(String user, String senha) throws ServiceExc{
        Usuario userLogin = usuarioRepo.buscarLogin(user,senha);
        return userLogin;
    }*/

}

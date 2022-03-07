package com.donutec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.donutec.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select i from Usuario i where i.email = :email")
    public Usuario findByEmail(String email);

    @Query("select j from Usuario j where j.nome = :nome and j.senha = :senha")
    public Usuario buscarLogin (String nome, String senha);

    //public Usuario finByUserAndSenha(String user, String senha);
}

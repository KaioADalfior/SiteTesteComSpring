package com.novoprojeto.sitezinho.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novoprojeto.sitezinho.Model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
}

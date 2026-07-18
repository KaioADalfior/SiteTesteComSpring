package com.novoprojeto.sitezinho.Repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.novoprojeto.sitezinho.Model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>
{
   
    Optional<Usuario> findByEmail(String email);    
    //Ao usar o findByEmail, o JPA do Spring data já entende que é para buscar por email. 
}

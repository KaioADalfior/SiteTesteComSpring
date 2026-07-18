package com.novoprojeto.sitezinho.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.novoprojeto.sitezinho.Model.Usuario;
import com.novoprojeto.sitezinho.Repositorio.UsuarioRepositorio;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//diz ao Spring que este método é um controlador.
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @PostMapping("/registro")
    public String salvar(Usuario usuario) {

        usuarioRepositorio.save(usuario);

        return "redirect:/login";
    }

    @GetMapping("/registro")
    public String abrirRegistro(Model model) {
        
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }
    

}
package com.novoprojeto.sitezinho.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.novoprojeto.sitezinho.Model.Usuario;
import com.novoprojeto.sitezinho.Repositorio.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



//diz ao Spring que este método é um controlador.
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;



    //rota de registro
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

    //rota de login
    @GetMapping("/login")
    public String abrirLogin(){
        return "login";
    }
    //realizar login
    @PostMapping("/login")
    public String realizarLogin(String email, String senha, Model model, HttpSession sessaoLogado) {
        Optional<Usuario> usuario = usuarioRepositorio.findByEmail(email);

        if(usuario.isPresent()){
            Usuario usuarioBanco = usuario.get();

            if(usuarioBanco.getSenha().equals(senha)){
                sessaoLogado.setAttribute("usuarioLogado", usuarioBanco);

                return "redirect:/home";
            }
        }

        model.addAttribute("erro", "E-mail ou senha inválidos!");

        return "login";
    }
    
    //rota de Home
    @GetMapping("/home")
    public String abrirHome(HttpSession sessaoLogado, Model model){
        Usuario usuario = (Usuario) sessaoLogado.getAttribute("usuarioLogado");

        model.addAttribute("usuario", usuario);
        return "home";
    }


    //realizar logout
    @GetMapping("/logout")
    public String logout(HttpSession sessaoLogout){
        sessaoLogout.invalidate();

        return "redirect:/login";
    }
}
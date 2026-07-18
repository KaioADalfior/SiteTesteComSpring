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


//diz ao Spring que este método é um controlador.
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;



    //rota de registro
    @PostMapping("/registro")
    public String salvar(Usuario usuario) {
<<<<<<< HEAD

        usuarioRepositorio.save(usuario);

=======
        usuarioRepositorio.save(usuario);
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
        return "redirect:/login";
    }

    @GetMapping("/registro")
    public String abrirRegistro(Model model) {
<<<<<<< HEAD
        
=======
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
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
<<<<<<< HEAD

            if(usuarioBanco.getSenha().equals(senha)){
                sessaoLogado.setAttribute("usuarioLogado", usuarioBanco);

=======
            if(usuarioBanco.getSenha().equals(senha)){
                sessaoLogado.setAttribute("usuarioLogado", usuarioBanco);
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
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
<<<<<<< HEAD

        model.addAttribute("usuario", usuario);
        return "home";
    }

=======
        model.addAttribute("usuario", usuario);
        return "home";
    }
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
    //rota da página inicial
    //quando digitar o local host 8080, entra direto no inicio.html
    @GetMapping("/")
    public String PaginaInicial()
    {
        return "inicio";
    }
    
<<<<<<< HEAD

=======
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
    //rota página de meus dados
    @GetMapping("/meusdados")
    public String abrirMeusDados(HttpSession sessaoLogado, Model model){
        Usuario usuario = (Usuario) sessaoLogado.getAttribute("usuarioLogado");

        if(usuario == null){
            return "redirect:/login";
        }
<<<<<<< HEAD
        
=======
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
        model.addAttribute("usuario", usuario);
        return "meusdados";
    }

    //alterar os dados
    @PostMapping("/meusdados")
    public String atualizarMeusDados(Usuario usuario, HttpSession sessao){
        usuarioRepositorio.save(usuario);
<<<<<<< HEAD

        sessao.setAttribute("usuarioLogado", usuario);

        return "redirect:/meusdados";
    }
    


=======
        sessao.setAttribute("usuarioLogado", usuario);
        return "redirect:/meusdados";
    }
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
    //realizar logout
    @GetMapping("/logout")
    public String logout(HttpSession sessaoLogout){
        sessaoLogout.invalidate();
<<<<<<< HEAD

=======
>>>>>>> 8c12d80 (Correção bug de duplicação ao editar)
        return "redirect:/login";
    }
}
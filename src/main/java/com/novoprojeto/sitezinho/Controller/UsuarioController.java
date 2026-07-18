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

    //rota da página inicial
    //quando digitar o local host 8080, entra direto no inicio.html
    @GetMapping("/")
    public String PaginaInicial()
    {
        return "inicio";
    }

    //rota página de meus dados
    @GetMapping("/meusdados")
    public String abrirMeusDados(HttpSession sessaoLogado, Model model){
        Usuario usuario = (Usuario) sessaoLogado.getAttribute("usuarioLogado");

        if(usuario == null){
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        return "meusdados";
    }

    //alterar os dados
    @PostMapping("/meusdados")
    public String atualizarMeusDados(Usuario usuario, String senhaAtual,
        String novaSenha,String confirmarSenha,HttpSession sessao,
         org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes){

        Usuario usuarioBanco = usuarioRepositorio.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean querTrocarSenha = (novaSenha != null && !novaSenha.isBlank())
                || (confirmarSenha != null && !confirmarSenha.isBlank())
                || (senhaAtual != null && !senhaAtual.isBlank());

        if (querTrocarSenha) {

            if (senhaAtual == null || !usuarioBanco.getSenha().equals(senhaAtual)) {
                redirectAttributes.addFlashAttribute("erro", "Senha atual incorreta.");
                return "redirect:/meusdados";
            }

            if (novaSenha == null || novaSenha.isBlank()) {
                redirectAttributes.addFlashAttribute("erro", "Digite a nova senha.");
                return "redirect:/meusdados";
            }

            if (!novaSenha.equals(confirmarSenha)) {
                redirectAttributes.addFlashAttribute("erro", "A confirmação de senha não coincide.");
                return "redirect:/meusdados";
            }

            usuarioBanco.setSenha(novaSenha);
        }
        // Se nenhum campo de senha foi preenchido, a senha atual é mantida.

        usuarioBanco.setNome(usuario.getNome());
        usuarioBanco.setEmail(usuario.getEmail());
        usuarioBanco.setTelefone(usuario.getTelefone());

        usuarioRepositorio.save(usuarioBanco);

        sessao.setAttribute("usuarioLogado", usuarioBanco);

        redirectAttributes.addFlashAttribute("sucesso", "Dados atualizados com sucesso!");
        return "redirect:/meusdados";
    }

    //realizar logout
    @GetMapping("/logout")
    public String logout(HttpSession sessaoLogout){
        sessaoLogout.invalidate();

        return "redirect:/login";
    }
}
package com.novoprojeto.sitezinho.Config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//impedir que usuarios que não tem conta acessem a home;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest requisicao, 
        HttpServletResponse resposta, Object handler) throws Exception
        {
            HttpSession sessao = requisicao.getSession();

            Object usuario = sessao.getAttribute("usuarioLogado");

            if(usuario == null){
                resposta.sendRedirect("/login");
                
                return false;
            }

            return true;
        }
}

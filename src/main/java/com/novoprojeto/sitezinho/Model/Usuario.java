package com.novoprojeto.sitezinho.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //dizer ao java que o banco de dados cria automaticamente a chave primária pelo ID;
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String telefone;

    //construtor vazio, obrigatório para o JPA
    public Usuario(){

    }

    //declarar getters e setters


    //id
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }


    //nome
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    //email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    //senha
    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    //telefone
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

}

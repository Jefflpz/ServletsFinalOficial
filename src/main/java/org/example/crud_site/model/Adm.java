package org.example.crud_site.model;

import java.util.UUID;

public class Adm {

    // atributos da classe
    private UUID id;
    private String username;
    private String senha;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public Adm(UUID id, String  username, String senha) {

        // Atribuindo os valores dos parâmetros aos atributos da classe
        this.id = id;
        this.username =  username;
        this.senha = senha;
    }
    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe sem o id
    public Adm(String  username, String senha) {

        // Atribuindo os valores dos parâmetros aos atributos da classe
        this.username =  username;
        this.senha = senha;

    }


    // Métodos Getters que retornam os valores dos atributos da classe
    public UUID getId() {
        // Retorna o valor do atributo id
        return this.id;
    }
    public String getUsername() {
        // Retorna o valor do atributo
        // username
        return this.username;
    }
    public String getSenha() {
        // Retorna o valor do atributo senha
        return this.senha;
    }

    // Métodos Setters que alteram os valores dos atributos da classe
    public void setSenha(String senha) {
        // Recebe um valor (String) e altera o valor do atributo senha
        this.senha = senha;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    public String toString() {
        return "Adm{id:" + id + ",  username:" +  username + ", senha:" + senha + "}";
    }
}

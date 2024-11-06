package org.example.crud_site.model;

// Importando a biblioteca UUID para gerar IDs únicos para cada objeto da classe
import java.util.UUID;

// Classe Setor
public class Setor {
    // Atributos da classe Setor
    private UUID id;
    private String nome;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public Setor(String nome) {
        this.nome = nome;
    }

    public Setor(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Métodos Getters que retornam os valores dos atributos da classe
    public UUID getId() {
        // Retorna o valor do atributo Id
        return id;
    }
    public String getNome() {
        // Retorna o valor do atributo Nome
        return nome;
    }

    // Métodos Setters que alteram os valores dos atributos da classe
    public void setNome(String nome) {
        // Recebe um valor String e altera o valor do atributo nome
        this.nome = nome;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    public String toString() {
        return "Setor {" +
                "\nid: " + this.id +
                ";\nnome: '" + this.nome +
                "\n}";
    }
}

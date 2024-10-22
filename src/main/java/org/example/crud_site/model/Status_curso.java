package org.example.crud_site.model;
// Importando a biblioteca UUID para gerar IDs únicos para cada objeto da classe
import java.util.UUID;

// Classe Status_curso
public class Status_curso {
    // Atributos da classe
    private UUID id;
    private String nome;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public Status_curso(String nome) {
        this.nome = nome;
    }
    public Status_curso(UUID id,String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Métodos Getters que retornam os valores dos atributos da classe.;~~~~~~~~~~~~~~~~
    public UUID getId() {
        // Retorna o valor do atributo id.
        return id;
    }
    public String getNome() {
        // Retorna o valor do atributo nome.
        return nome;
    }

    // Métodos Setters que alteram os valores dos atributos da classe.
    public void setNome(String nome) {
        // Recebe um valor String e altera o valod do atributo nome.
        this.nome = nome;
    }

    // Método toString que retorna uma representação textual do objeto da classe.
    public String toString() {
        return "Status_curso{" +
                "\nid: " + id +
                ";\nnome: " + nome +
                "\n";
    }
}

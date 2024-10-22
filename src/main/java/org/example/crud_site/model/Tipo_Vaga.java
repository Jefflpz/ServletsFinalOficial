package org.example.crud_site.model;
//Importando a biblioteca UUID para gerar IDs únicos para cada objeto da classe
import java.util.UUID;

// Classe Tipo_Vaga
public class Tipo_Vaga {

    // Atributos da classe
    private UUID id;
    private String nome;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public Tipo_Vaga(String nome) {
        this.nome = nome;
    }
    public Tipo_Vaga(UUID id,String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Métodos Getters que retornam os valores dos atributos da classe
    public UUID getId() {
        // Retorna o Valor do Atributo Id
        return id;
    }
    public String getNome() {
        // Retorna o Valor do Atributo Nome
        return nome;
    }

    // Métodos Setters que alteram os valores dos atributos da classe
    public void setNome(String nome) {
        // Recebe um valor do tipo String e altera o valor do Atributo Nome
        this.nome = nome;
    }

    // Método toString que retorna uma representação textual do objeto
    @Override
    public String toString() {
        return "Tipo_Vaga{" +
                "\nid: " + id +
                ";\nnome: " + nome +
                "}";
    }
}

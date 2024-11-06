package org.example.crud_site.model;

//Importando a biblioteca UUID para gerar IDs únicos para cada objeto da classe
import java.util.UUID;

// Classe Tipo_Arquivo
public class TipoArquivo {
    // Atributos da classe
    private UUID id;
    private String nome;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public TipoArquivo(String nome) {
        this.nome = nome;
    }
    public TipoArquivo(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Métodos Getters que retornam os valores dos atributos da classe
    public UUID getId() {
       // Retorna o valor do atributo id
        return id;
    }
    public String getNome() {
        // Retorna o valor do atributo nome
        return nome;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    @Override
    public String toString() {
        return "Tipo Arquivo {" +
                "\nId: " + id +
                ";\nNome: " + nome +
                "\n}";
    }
}

package org.example.crud_site.model;

// Importando a biblioteca UUID para gerar um identificador único para o arquivo
import java.util.UUID;

// Classe Vaga
public class Vaga {

    // Atributos da classe
    private UUID id;
    private String nome;
    private String descricao;
    private UUID id_empresa;

    // Método construtor que recebe os parâmetros necessários para criar
    // um objeto da classe Vaga a atribui os valores aos atributos da classe
    public Vaga(String nome, String descricao, UUID id_empresa) {
        this.nome = nome;
        this.descricao = descricao;
        this.id_empresa = id_empresa;
    }
    public Vaga() {}

    // Métodos Getters, que acesso os atributos da classe
    public UUID getId() {return this.id;}
    public String getNome() {return this.nome;}
    public String getDescricao() {return this.descricao;}
    public UUID getId_empresa() {return this.id_empresa;}

    // Método Setters, que modifica os atributos da classe
    public void setNome(String nome) {this.nome = nome;}
    public void setDescricao(String descricao) {this.descricao = descricao;}


    // Método toString uma representação do objeto em forma de 'String'
    public String toString() {
        return "Vaga: " + this.id +
                "\nNome: " + this.nome +
                "\nDescricao: " + this.descricao +
                "\nid_empresa: " + this.id_empresa;
    }
}

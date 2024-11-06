package org.example.crud_site.model;

//importando a classe UUID para gerar um identificador único para a classe Curso
import java.util.UUID;

// Classe Curso
public class Curso {

    // Atributos da Classe
    private UUID id;
    private String nome;
    private String descricao;
    private char status;
    private UUID idConta;
    private UUID id_autorizador;

    // Construtor que recebe os parâmetros necessários para criar um objeto da classe Curso
    // e atribui os valores aos atributos da classe
    public Curso(String nome, String descricao, UUID id_conta) {
        this.nome = nome;
        this.descricao = descricao;
        this.idConta = id_conta;
    }
    public Curso(UUID idConta, String nome, String descricao, UUID idCurso, UUID idAutorizador) {
        this.idConta = idConta;
        this.nome = nome;
        this.descricao = descricao;
        this.id = idCurso;
        this.id_autorizador = idAutorizador;
    }

    // Métodos Getters, que acessa os atributos da classe
    public UUID getIdConta() {
        return this.idConta;
    }
    public UUID getIdAutorizador() {
        return this.id_autorizador;
    }
    public UUID getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public char getStatus() {
        return this.status;
    }

    // Métodos 'Setters', que alteram os atributos da classe
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public void setStatus(char status) {this.status = status;}


    // Método toString, uma representação do objeto em forma de String
    public String toString() {
        return "Curso {" +
                "\n Id: " + this.id +
                "\n Nome: " + this.nome +
                "\n Descricao: " + this.descricao +
                "\n Status: " + this.status;
    }
}

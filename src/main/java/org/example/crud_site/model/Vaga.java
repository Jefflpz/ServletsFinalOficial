package org.example.crud_site.model;

// Importando a biblioteca UUID para gerar um identificador único para o arquivo
import java.util.UUID;

// Classe Vaga
public class Vaga {

    // Atributos da classe
    private UUID id;
    private UUID id_tipo;
    private String nome;
    private String descricao;
    private UUID idEmpresa;

    // Método construtor que recebe os parâmetros necessários para criar
    // um objeto da classe Vaga a atribui os valores aos atributos da classe
    public Vaga(String nome, String descricao, UUID idEmpresa) {
        this.nome = nome;
        this.descricao = descricao;
        this.idEmpresa = idEmpresa;
    }
    public Vaga(UUID id,UUID id_tipo, String nome, String descricao, UUID id_empresa) {
        this.id = id;
        this.id_tipo = id_tipo;
        this.nome = nome;
        this.descricao = descricao;
        this.idEmpresa = id_empresa;
    }

    // Métodos Getters, que acesso os atributos da classe
    public UUID getId() {
        return id;
    }
    public UUID getId_tipo() {
        return id_tipo;
    }
    public String getNome() {return this.nome;}
    public String getDescricao() {return this.descricao;}
    public UUID getIdEmpresa() {return this.idEmpresa;}

    // Método Setters, que modifica os atributos da classe
    public void setNome(String nome) {this.nome = nome;}
    public void setDescricao(String descricao) {this.descricao = descricao;}


    // Método toString uma representação do objeto em forma de 'String'
    public String toString() {
        return "Vaga { "+
                "\nNome: " + this.nome +
                "\nDescricao: " + this.descricao +
                "\nId Empresa: " + this.idEmpresa +
                " }";
    }
}

package org.example.crud_site.model;

// Importando a biblioteca Date para trabalhar com datas
// Importando a biblioteca UUID para gerar IDs únicos para cada objeto da classe
import java.util.UUID;

// Classe Permissao_Vaga
public class Permissao_Vaga {
    //Atributos da classe
    private UUID id;
    private UUID id_empresa;
    private boolean permissao;
    private String data_solicitacao;
    private String data_autorizacao;
    private UUID id_vaga;
    private UUID id_autorizador;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public Permissao_Vaga( UUID id_empresa, boolean permissao, String dt_solicitaco, String dt_autorizador, UUID id_curso, UUID id_autorizador) {

        // Atribuindo os valores dos parâmetros aos atributos da classe
        this.id_empresa = id_empresa;
        this.permissao = permissao;
        this.data_solicitacao = dt_solicitaco;
        this.data_autorizacao = dt_autorizador;
        this.id_vaga = id_curso;
        this.id_autorizador = id_autorizador;
    }

    // Métodos Getters que retornam os valores dos atributos da classe
    public UUID getId() {
        // Retorna o valor do atributo id
        return id;
    }
    public UUID getId_empresa() {
        // Retorna o valor do atributo id_empresa
        return id_empresa;
    }
    public boolean permissao() {
        // Retorna o valor do atributo permissao
        return permissao;
    }
    public String getData_solicitacao() {
        // Retorna o valor do atributo data_solicitacao
        return data_solicitacao;
    }
    public String getData_autorizacao() {
        // Retorna o valor do atributo data_autorizacao
        return data_autorizacao;
    }
    public UUID getId_vaga() {
        // Retorna o valor do atributo id_vaga
        return id_vaga;
    }
    public UUID getId_autorizador() {
        // Retorna o valor do atributo id_autorizador
        return id_autorizador;
    }

    // Métodos 'Setter's que alteram os valores dos atributos da classe
    public void setPermissao(boolean permissao) {
        // Recebe um valor do tipo boolean e altera o valor do atributo permissao
        this.permissao = permissao;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    public String toString() {
        return "Permissao_Vaga{" +
                "\nid: " + id +
                ";\nid_empresa: " + id_empresa +
                ";\npermissao: " + permissao +
                ";\ndata_solicitacao: " + data_solicitacao +
                ";\ndata_autorizacao: " + data_autorizacao +
                ";\nid_vaga: " + id_vaga +
                ";\nid_autorizador: " + id_autorizador +
                "\n}";
    }
}
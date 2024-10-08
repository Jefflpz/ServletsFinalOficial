package org.example.crud_site.model;

// Importando a biblioteca Date para trabalhar com datas

import java.util.Date;
import java.util.UUID;

// Classe Permissao_Vaga
public class Permissao_Vaga {
    //Atributos da classe
    private UUID id;
    private UUID id_empresa;
    private boolean permissao;
    private Date data_solicitacao;
    private Date data_autorizacao;
    private UUID id_vaga;
    private UUID id_autorizador;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public Permissao_Vaga(boolean permissao, Date data_solicitacao, Date data_autorizacao) {
        this.permissao = permissao;
        this.data_solicitacao = data_solicitacao;
        this.data_autorizacao = data_autorizacao;
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
    public Date getData_solicitacao() {
        // Retorna o valor do atributo data_solicitacao
        return data_solicitacao;
    }
    public Date getData_autorizacao() {
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
    public void setData_autorizacao(Date data_autorizacao) {
        // Recebe um valor do tipo Date e altera o valor do atributo data_autorizacao
        this.data_autorizacao = data_autorizacao;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    @Override
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
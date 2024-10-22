package org.example.crud_site.model;

import java.util.UUID;

public class Permissao_Curso{

    //Atributos da classe
    private UUID id;
    private UUID id_conta;
    private boolean permissao = false;
    private String dt_solicitaco;
    private String dt_autorizacao;
    private UUID id_curso;
    private UUID id_autorizador;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public Permissao_Curso( UUID id_conta, boolean permissao, String dt_solicitaco, String dt_autorizador, UUID id_curso, UUID id_autorizador) {

        // Atribuindo os valores dos parametros aos atributos da classe
        this.id_conta = id_conta;
        this.permissao = permissao;
        this.dt_solicitaco = dt_solicitaco;
        this.dt_autorizacao = dt_autorizador;
        this.id_curso = id_curso;
        this.id_autorizador = id_autorizador;
    }

    // Métodos Getters que retornam os valores dos atributos da classe

    public UUID getId() {
        // Retorna o valor do atributo id
        return this.id;
    }
    public UUID getId_conta() {
        // Retorna o valor do atributo id_conta
        return id_conta;
    }
    public boolean isPermissao() {
        // Retorna o valor do atributo permissao
        return permissao;
    }
    public String getDt_solicitaco() {
        // Retorna o valor do atributo dt_solicitaco
        return dt_solicitaco;
    }
    public String getDt_autorizador() {
        // Retorna o valor do atributo dt_autorizador
        return dt_autorizacao;
    }
    public UUID getId_curso() {
        // Retorna o valor do atributo id_curso
        return id_curso;
    }
    public UUID getId_autorizador() {
        // Retorna o valor do atributo id_autorizador
        return id_autorizador;
    }
    // Métodos 'Setter's que alteram os valores dos atributos da classe
    public void setPermissao(boolean permissao) {
        // Recebe um valor booleano e altera o valor do atributo permissao
        this.permissao = permissao;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    public String toString() {
        return "Permissao_Curso{" +
                "\nid: " + this.id +
                ";\nid_conta: " + this.id_conta +
                ";\npermissao: " + this.permissao +
                ";\ndt_solicitaco: " + this.dt_solicitaco +
                ";\ndt_autorizacao: " + this.dt_autorizacao +
                ";\nid_curso: " + this.id_curso +
                ";\nid_autorizador: " + this.id_autorizador +
                "\n}";
    }
}

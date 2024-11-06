package org.example.crud_site.model;

import java.util.UUID;

public class PermissaoCurso extends Curso {

    //Atributos da classe
    private UUID id;
    private UUID idConta;
    private boolean permissao = false;
    private String dtSolicitaco;
    private String dtAutorizacao;
    private UUID idCurso;
    private UUID idAutorizador;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe

    public PermissaoCurso(String nome, String descricao, UUID id_conta, UUID id_autorizador, UUID id_curso, String dt_autorizacao, String dt_solicitaco, boolean permissao, UUID id_conta1, UUID id) {
        super(nome, descricao, id_conta);
        this.idAutorizador = id_autorizador;
        this.idCurso = id_curso;
        this.dtAutorizacao = dt_autorizacao;
        this.dtSolicitaco = dt_solicitaco;
        this.permissao = permissao;
        this.idConta = id_conta1;
        this.id = id;
    }
    public PermissaoCurso(UUID id_autorizador, UUID id_curso, String dt_autorizacao, String dt_solicitaco, boolean permissao, UUID id, UUID id_conta) {
        super("","",UUID.randomUUID());
        this.idAutorizador = id_autorizador;
        this.idCurso = id_curso;
        this.dtAutorizacao = dt_autorizacao;
        this.dtSolicitaco = dt_solicitaco;
        this.permissao = permissao;
        this.idConta = id_conta;
        this.id = id;
    }
    // Métodos Getters que retornam os valores dos atributos da classe

    public UUID getId() {
        // Retorna o valor do atributo id
        return this.id;
    }
    public UUID getIdConta() {
        // Retorna o valor do atributo id_conta
        return idConta;
    }
    public boolean getPermissao() {
        // Retorna o valor do atributo permissao
        return permissao;
    }
    public String getDtSolicitaco() {
        // Retorna o valor do atributo dt_solicitaco
        return dtSolicitaco;
    }
    public String getDtAutorizador() {
        // Retorna o valor do atributo dt_autorizador
        return dtAutorizacao;
    }
    public UUID getIdCurso() {
        // Retorna o valor do atributo id_curso
        return idCurso;
    }
    public UUID getIdAutorizador() {
        // Retorna o valor do atributo id_autorizador
        return idAutorizador;
    }
    // Métodos 'Setter's que alteram os valores dos atributos da classe
    public void setPermissao(boolean permissao) {
        // Recebe um valor booleano e altera o valor do atributo permissao
        this.permissao = permissao;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    public String toString() {
        return "Permissao_Curso {" +
                "\nId: " + this.id +
                ";\nId Conta: " + this.idConta +
                ";\nPermissao: " + this.permissao +
                ";\nDt Solicitaco: " + this.dtSolicitaco +
                ";\nDt Autorizacao: " + this.dtAutorizacao +
                ";\nId Curso: " + this.idCurso +
                ";\nId autorizador: " + this.idAutorizador +
                "\n}";
    }
}

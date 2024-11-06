package org.example.crud_site.model;

// Importando a biblioteca Date para trabalhar com datas
// Importando a biblioteca UUID para gerar IDs únicos para cada objeto da classe
import java.util.UUID;

// Classe Permissao_Vaga
public class PermissaoVaga extends Vaga {
    //Atributos da classe
    private UUID id;
    private UUID idEmpresa;
    private boolean permissao;
    private String dataSolicitacao;
    private String dataAutorizacao;
    private UUID idVaga;
    private UUID idAutorizador;

    // Método construtor que recebe os parâmetros necessários para criar um objeto da classe
    public PermissaoVaga(String nome, String descricao, UUID id_empresa, UUID id, UUID id_empresa1, boolean permissao, String data_solicitacao, String data_autorizacao, UUID id_vaga, UUID id_autorizador) {
        super(nome, descricao, id_empresa);
        this.id = id;
        this.idEmpresa = id_empresa1;
        this.permissao = permissao;
        this.dataSolicitacao = data_solicitacao;
        this.dataAutorizacao = data_autorizacao;
        this.idVaga = id_vaga;
        this.idAutorizador = id_autorizador;
    }
    public PermissaoVaga(UUID id, UUID idEmpresa, boolean permissao, String dataSolicitacao, String dataAutorizacao, UUID idVaga, UUID idAutorizador) {
        super();
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.permissao = permissao;
        this.dataSolicitacao = dataSolicitacao;
        this.dataAutorizacao = dataAutorizacao;
        this.idVaga = idVaga;
        this.idAutorizador = idAutorizador;
    }

    // Métodos Getters que retornam os valores dos atributos da classe
    public UUID getId() {
        // Retorna o valor do atributo id
        return id;
    }
    public UUID getIdEmpresa() {
        // Retorna o valor do atributo id_empresa
        return idEmpresa;
    }
    public boolean getPermissao() {
        // Retorna o valor do atributo permissao
        return permissao;
    }
    public String getDtSolicitacao() {
        // Retorna o valor do atributo data_solicitacao
        return dataSolicitacao;
    }
    public String getDtAutorizacao() {
        // Retorna o valor do atributo data_autorizacao
        return dataAutorizacao;
    }
    public UUID getIdVaga() {
        // Retorna o valor do atributo id_vaga
        return idVaga;
    }
    public UUID getIdAutorizador() {
        // Retorna o valor do atributo id_autorizador
        return idAutorizador;
    }

    // Métodos 'Setter's que alteram os valores dos atributos da classe
    public void setPermissao(boolean permissao) {
        // Recebe um valor do tipo boolean e altera o valor do atributo permissao
        this.permissao = permissao;
    }

    // Método toString que retorna uma representação textual do objeto da classe
    public String toString() {
        return "Permissao_Vaga{" +
                "\nId: " + id +
                ";\nId Empresa: " + idEmpresa +
                ";\nPermissao: " + permissao +
                ";\nData Solicitacao: " + dataSolicitacao +
                ";\nData Autorizacao: " + dataAutorizacao +
                ";\nId Vaga: " + idVaga +
                ";\nId Autorizador: " + idAutorizador +
                "\n}";
    }
}
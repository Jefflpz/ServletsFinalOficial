package org.example.crud_site.dao;

// Importando a classe Tipo_Arquivo para manipular os dados relacionados.

import org.example.crud_site.model.Tipo_Arquivo;

//importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de tipos de arquivos.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Tipo_ArquivoDAO
public class Tipo_ArquivoDAO {

    // Objeto responsável por gerenciar a conexão com o banco de dados
    private Conexao conexao;

    //Construtor atribui a conexao uma nova Conexao() com os atribuitos da classe Conexao.
    public Tipo_ArquivoDAO() {
        conexao = new Conexao();
    }

    //Metodo para inserir um novo tipo de arquivo
    public boolean inserir(Tipo_Arquivo tipo_arquivo) {
        conexao.conectar();

        try {

            // Instrução SQL para inserir registro na tabela Setor
            String sql = "INSERT INTO tipo_arquivo (nome) VALUES (?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmtros de consulta
            conexao.pstmt.setString(1, tipo_arquivo.getNome());

            // Executa a instrução SQL
            return conexao.pstmt.executeUpdate() > 0;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conexao.desconectar();
        }
    }
    // Método para alterar o nome de um setor na tabela Setor
    public boolean alterarNome(Tipo_Arquivo tipo_arquivo, String nome) {

        conexao.conectar();
        try {
            String sql = "UPDATE tipo_arquivo SET nome = ? WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros de consulta SQL
            conexao.pstmt.setString(1, nome);
            conexao.pstmt.setString(2, tipo_arquivo.getNome());

            // Executa a instrução SQL.
            int rows = conexao.pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro.
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o registro.", e);
        }finally {
            conexao.desconectar();
        }
    }
    // Método para excluir um Setor na tabela Setor
    public void excluirTipo_Arquivo(String nomeTipo_Arquivo) {
        conexao.conectar();
        try{
            // Instrução SQL para excluir uma situação_trabalhista na tabela Adm
            String sql = "DELETE FROM tipo_arquivo WHERE nome=?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL
            conexao.pstmt.setString(1, nomeTipo_Arquivo);

            // executa instrução SQL
            conexao.pstmt.execute();
        }catch (SQLException e){
            throw new RuntimeException("Erro ao excluir o registro.", e);
        }finally {
            conexao.desconectar();
        }
    }
    // Método para buscar um administrador na tabela Adm
    public Tipo_Arquivo buscarTipo_Arquivo(String nomeTipo_Arquivo) {
        conexao.conectar();

        try {

            // Instrução SQL para excluir um administrador na tabela Adm
            String sql = "SELECT * FROM tipo_arquivo WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, nomeTipo_Arquivo);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            if (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);

                // Cria um objeto Administrador com os dados do ResultSet
                return new Tipo_Arquivo(id, nome);
            }else {
                return null;
            }
        }catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    // Método para listar todos os setores na tabela Setor
    public   List<Tipo_Arquivo> listarTipo_Arquivo() {

        // Instrução SQL para listar todos os administradores na tabela Adm
        String sql = "SELECT * FROM tipo_arquivo";

        // Essa linha cria uma lista vazia chamada adms, pronta para armazenar objetos do tipo Adm.
        // Usamos o ArrayList para faciliar a manipulação da lista.
        List<Tipo_Arquivo> tipos_arquivos = new ArrayList<>();

        conexao.conectar();

        try {

            // Prepara a instrução SQL para executar a consulta.
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            // Armazena o resultado da consulta no objeto ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtem os dados do ResultSet e armazena na lista de administradores.
            // Usamos o while para percorrer o ResultSet caso haja mais de um registro.
            while (conexao.rs.next()) {

                // Pega os dados do ResultSet
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);

                // Cria um objeto Adm com os dados do ResultSet
                Tipo_Arquivo tipo_arquivo = new Tipo_Arquivo(id,nome);


                // Adiciona o objeto Adm na lista de administradores
                tipos_arquivos.add(tipo_arquivo);
            }
        }catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }

        // Retorna a lista de administradores
        return tipos_arquivos;
    }
}

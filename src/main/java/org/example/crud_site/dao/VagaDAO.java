package org.example.crud_site.dao;

import org.example.crud_site.model.Vaga;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe VagaDAO, responsável pelo CRUD na tabela Vaga
public class VagaDAO {

    // Objeto para gerenciar a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa uma nova conexão com o banco de dados
    public VagaDAO() {
        conexao = new Conexao();
    }

    // Método para inserir uma nova vaga na tabela Vaga
    public boolean inserirVaga(String nome, String descricao, UUID idEmpresa) {
        conexao.conectar();
        try {
            // Instrução SQL para inserir uma vaga na tabela Vaga
            String sql = "INSERT INTO vaga (nome, descricao, id_empresa) VALUES (?, ?, ?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta
            conexao.pstmt.setString(1, nome);
            conexao.pstmt.setString(2, descricao);
            conexao.pstmt.setObject(3, idEmpresa);

            // Executa a instrução SQL e retorna true se a inserção foi bem-sucedida
            return conexao.pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para alterar o nome e a descrição de uma vaga na tabela Vaga
    public boolean alterarVaga(UUID id, String novoNome, String novaDescricao) {
        conexao.conectar();
        try {
            // Instrução SQL para atualizar o nome e a descrição de uma vaga
            String sql = "UPDATE vaga SET nome = ?, descricao = ? WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL
            conexao.pstmt.setString(1, novoNome);
            conexao.pstmt.setString(2, novaDescricao);
            conexao.pstmt.setObject(3, id);

            // Executa a instrução SQL e verifica se algum registro foi alterado
            int rows = conexao.pstmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para excluir uma vaga na tabela Vaga
    public void excluirVaga(UUID id) {
        conexao.conectar();
        try {
            // Instrução SQL para excluir uma vaga na tabela Vaga
            String sql = "DELETE FROM vaga WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na consulta SQL
            conexao.pstmt.setObject(1, id);

            // Executa a instrução SQL de exclusão
            conexao.pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir a vaga.", e);
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para buscar uma vaga específica pelo ID
    public Vaga buscarVaga(UUID id) {
        conexao.conectar();
        try {
            // Instrução SQL para buscar uma vaga pelo ID
            String sql = "SELECT * FROM vaga WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setObject(1, id);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtém os dados do ResultSet e cria um objeto Vaga
            if (conexao.rs.next()) {
                UUID vagaId = (UUID) conexao.rs.getObject("id");
                String nome = conexao.rs.getString("nome");
                String descricao = conexao.rs.getString("descricao");
                UUID idEmpresa = (UUID) conexao.rs.getObject("id_empresa");

                return new Vaga(nome, descricao, idEmpresa);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para listar todas as vagas na tabela Vaga
    public List<Vaga> listarVagas() {
        // Instrução SQL para listar todas as vagas na tabela Vaga
        String sql = "SELECT * FROM vaga";

        // Lista para armazenar as vagas recuperadas
        List<Vaga> vagas = new ArrayList<>();
        conexao.conectar();

        try {
            // Prepara a instrução SQL para executar a consulta
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtém os dados do ResultSet e armazena na lista de vagas
            while (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject("id");
                String nome = conexao.rs.getString("nome");
                String descricao = conexao.rs.getString("descricao");
                UUID idEmpresa = (UUID) conexao.rs.getObject("id_empresa");

                Vaga vaga = new Vaga(nome, descricao, idEmpresa);
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }

        // Retorna a lista de vagas
        return vagas;
    }
}


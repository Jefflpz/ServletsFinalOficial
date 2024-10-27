package org.example.crud_site.dao;

import org.example.crud_site.model.Curso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe CursoDAO, responsável pelo CRUD na tabela Curso
public class CursoDAO {

    // Objeto para gerenciar a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa uma nova conexão com o banco de dados
    public CursoDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo curso na tabela Curso
    public boolean inserirCurso(String nome, String descricao, UUID idConta) {
        conexao.conectar();
        try {
            // Instrução SQL para inserir um curso na tabela Curso
            String sql = "INSERT INTO curso (nome, descricao, id_conta) VALUES (?, ?, ?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta
            conexao.pstmt.setString(1, nome);
            conexao.pstmt.setString(2, descricao);
            conexao.pstmt.setObject(3, idConta);

            // Executa a instrução SQL e retorna true se a inserção foi bem-sucedida
            return conexao.pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para alterar o nome e descrição de um curso na tabela Curso
    public boolean alterarCurso(UUID id, String novoNome, String novaDescricao) {
        conexao.conectar();
        try {
            // Instrução SQL para atualizar nome e descrição de um curso
            String sql = "UPDATE curso SET nome = ?, descricao = ? WHERE id = ?";
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

    // Método para excluir um curso na tabela Curso
    public void excluirCurso(UUID id) {
        conexao.conectar();
        try {
            // Instrução SQL para excluir um curso na tabela Curso
            String sql = "DELETE FROM curso WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na consulta SQL
            conexao.pstmt.setObject(1, id);

            // Executa a instrução SQL de exclusão
            conexao.pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o curso.", e);
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para buscar um curso específico pelo ID
    public Curso buscarCurso(UUID id) {
        conexao.conectar();
        try {
            // Instrução SQL para buscar um curso pelo ID
            String sql = "SELECT * FROM curso WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setObject(1, id);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtém os dados do ResultSet e cria um objeto Curso
            if (conexao.rs.next()) {
                UUID cursoId = (UUID) conexao.rs.getObject("id");
                String nome = conexao.rs.getString("nome");
                String descricao = conexao.rs.getString("descricao");
                char status = conexao.rs.getString("status").charAt(0);
                UUID idConta = (UUID) conexao.rs.getObject("id_conta");

                Curso curso = new Curso(nome, descricao, idConta);
                curso.setStatus(status);
                return curso;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para listar todos os cursos na tabela Curso
    public List<Curso> listarCursos() {
        // Instrução SQL para listar todos os cursos na tabela Curso
        String sql = "SELECT * FROM curso";

        // Lista para armazenar os cursos recuperados
        List<Curso> cursos = new ArrayList<>();
        conexao.conectar();

        try {
            // Prepara a instrução SQL para executar a consulta
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtém os dados do ResultSet e armazena na lista de cursos
            while (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject("id");
                String nome = conexao.rs.getString("nome");
                String descricao = conexao.rs.getString("descricao");
                char status = conexao.rs.getString("status").charAt(0);
                UUID idConta = (UUID) conexao.rs.getObject("id_conta");

                Curso curso = new Curso(nome, descricao, idConta);
                curso.setStatus(status);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }

        // Retorna a lista de cursos
        return cursos;
    }
}

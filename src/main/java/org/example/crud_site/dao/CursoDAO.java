package org.example.crud_site.dao;

import org.example.crud_site.model.Curso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO curso (nome, descricao, id_conta) VALUES (?, ?, ?)")){

            // Define os valores dos parâmetros da consulta
            pstmt.setString(1, nome);
            pstmt.setString(2, descricao);
            pstmt.setObject(3, idConta);

            // Executa a instrução SQL e retorna true se a inserção foi bem-sucedida
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para alterar o nome e descrição de um curso na tabela Curso
    public boolean alterarCurso(UUID id, String novoNome, String novaDescricao) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE curso SET nome = ?, descricao = ? WHERE id = ?")){

            // Define os valores dos parâmetros na consulta SQL
            pstmt.setString(1, novoNome);
            pstmt.setString(2, novaDescricao);
            pstmt.setObject(3, id);

            // Executa a instrução SQL e verifica se algum registro foi alterado
            int rows = pstmt.executeUpdate();
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
    public boolean excluirCurso(UUID id) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM curso WHERE id = ?")){

            // Define o valor do parâmetro na consulta SQL
            pstmt.setObject(1, id);

            // Executa a instrução SQL de exclusão
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.desconectar();
        }
    }

    // Método para buscar um curso específico pelo ID
    public Curso buscarCurso(UUID id) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM curso WHERE id = ?")){

            pstmt.setObject(1, id);

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Obtém os dados do ResultSet e cria um objeto Curso
            if (rs.next()) {
                UUID cursoId = (UUID) rs.getObject("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                char status = rs.getString("status").charAt(0);
                UUID idConta = (UUID) rs.getObject("id_conta");

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

        // Lista para armazenar os cursos recuperados
        List<Curso> cursos = new ArrayList<>();
        conexao.conectar();

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM curso")){

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Obtém os dados do ResultSet e armazena na lista de cursos
            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                char status = rs.getString("status").charAt(0);
                UUID idConta = (UUID) rs.getObject("id_conta");

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

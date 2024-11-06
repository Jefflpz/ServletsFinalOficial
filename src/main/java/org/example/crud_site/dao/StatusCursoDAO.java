package org.example.crud_site.dao;

// Importando a classe Status_Curso para manipular os dados relacionados.
import org.example.crud_site.model.StatusCurso;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de status de curso.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe DAO responsável pelas operações de CRUD relacionadas ao status do curso.
public class StatusCursoDAO {

    // Objeto responsável por gerenciar a conexão com o banco de dados.
    private Conexao conexao;

    // Construtor que inicializa a conexão com uma nova instância de Conexao().
    public StatusCursoDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo status de curso no banco de dados.
    public boolean inserirStatusCurso(String status_curso) {
        conexao.conectar(); // Conecta ao banco de dados
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO Status_curso (nome) VALUES (?)")){

            // Define o valor do parâmetro 'nome' a partir do objeto Status_curso
            pstmt.setString(1, status_curso);

            // Executa a instrução SQL e retorna true se o número de linhas afetadas for maior que 0
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de um status de curso existente.
    public boolean alterarNomeStatusCurso(UUID uuid, String novoStatusCurso) {
        conexao.conectar(); // Conecta ao banco de dados
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE status_curso SET nome = ? WHERE id = ?")){

            // Define os valores dos parâmetros da consulta
            pstmt.setString(1, novoStatusCurso); // Novo nome
            pstmt.setObject(2, uuid); // Nome atual

            // Executa a instrução SQL e retorna true se alguma linha for alterada
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o registro.", e);
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }
    }

    // Método para excluir um status de curso pelo nome.
    public boolean excluirStautsCurso(String nomeStatus_curso) {
        conexao.conectar(); // Conecta ao banco de dados
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM status_curso WHERE nome = ?")){


            // Define o valor do parâmetro 'nome' a ser excluído
            pstmt.setString(1, nomeStatus_curso);

            // Executa a instrução SQL para exclusão
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }
    }

    // Método para buscar um status de curso pelo nome.
    public StatusCurso buscarStatusCurso(String nomeStatus_curso) {
        conexao.conectar(); // Conecta ao banco de dados
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM status_curso WHERE nome = ?")){

            pstmt.setString(1, nomeStatus_curso);

            // Executa a consulta e armazena o resultado
            ResultSet rs = pstmt.executeQuery();

            // Se o registro existir, cria um objeto Status_Curso com os dados obtidos
            if (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                return new StatusCurso(id, nome);
            }
            // Caso não encontre o status, retorna null
            return null;

        } catch (SQLException e) {
            return null;
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }
    }

    // Método para listar todos os status de curso cadastrados no banco de dados.
    public List<StatusCurso> listarStatusCurso() {

        // Cria uma lista vazia para armazenar os resultados
        List<StatusCurso> status_Cursos = new ArrayList<>();
        conexao.conectar(); // Conecta ao banco de dados

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM status_curso")){
            //  executa a consulta SQL

            ResultSet rs = pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, cria objetos Status_Curso e os adiciona à lista
            while (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                StatusCurso status_Curso = new StatusCurso(id, nome);
                status_Cursos.add(status_Curso);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }

        // Retorna a lista de status de curso
        return status_Cursos;
    }
}

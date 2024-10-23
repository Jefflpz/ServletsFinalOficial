package org.example.crud_site.dao;

// Importando a classe Status_Curso para manipular os dados relacionados.
import org.example.crud_site.model.Status_Curso;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de status de curso.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe DAO responsável pelas operações de CRUD relacionadas ao status do curso.
public class Status_CursoDAO {

    // Objeto responsável por gerenciar a conexão com o banco de dados.
    private Conexao conexao;

    // Construtor que inicializa a conexão com uma nova instância de Conexao().
    public Status_CursoDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo status de curso no banco de dados.
    public boolean inserirStatus_curso(Status_Curso status_curso) {
        conexao.conectar(); // Conecta ao banco de dados
        try {
            // Instrução SQL para inserir um novo status de curso na tabela
            String sql = "INSERT INTO Status_curso (nome) VALUES (?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro 'nome' a partir do objeto Status_curso
            conexao.pstmt.setString(1, status_curso.getNome());

            // Executa a instrução SQL e retorna true se o número de linhas afetadas for maior que 0
            return conexao.pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de um status de curso existente.
    public boolean alterarNomeSatus_curso(Status_Curso status_curso, String nome) {
        conexao.conectar(); // Conecta ao banco de dados
        try {
            // Instrução SQL para atualizar o nome do status de curso
            String sql = "UPDATE status_curso SET nome = ? WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta
            conexao.pstmt.setString(1, nome); // Novo nome
            conexao.pstmt.setString(2, status_curso.getNome()); // Nome atual

            // Executa a instrução SQL e retorna true se alguma linha for alterada
            int rows = conexao.pstmt.executeUpdate();
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
    public void excluirStauts_curso(String nomeStatus_curso) {
        conexao.conectar(); // Conecta ao banco de dados
        try {
            // Instrução SQL para excluir um status de curso
            String sql = "DELETE FROM status_curso WHERE nome=?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro 'nome' a ser excluído
            conexao.pstmt.setString(1, nomeStatus_curso);

            // Executa a instrução SQL para exclusão
            conexao.pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }
    }

    // Método para buscar um status de curso pelo nome.
    public Status_Curso buscarStatus_Curso(String nomeStatus_curso) {
        conexao.conectar(); // Conecta ao banco de dados
        try {
            // Instrução SQL para selecionar um status de curso pelo nome
            String sql = "SELECT * FROM status_curso WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, nomeStatus_curso);

            // Executa a consulta e armazena o resultado
            conexao.rs = conexao.pstmt.executeQuery();

            // Se o registro existir, cria um objeto Status_Curso com os dados obtidos
            if (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);
                return new Status_Curso(id, nome);
            } else {
                // Caso não encontre o status, retorna null
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            // Desconecta do banco de dados após a operação
            conexao.desconectar();
        }
    }

    // Método para listar todos os status de curso cadastrados no banco de dados.
    public List<Status_Curso> listarStatus_Curso() {
        // Instrução SQL para listar todos os registros na tabela status_curso
        String sql = "SELECT * FROM status_curso";

        // Cria uma lista vazia para armazenar os resultados
        List<Status_Curso> status_Cursos = new ArrayList<>();
        conexao.conectar(); // Conecta ao banco de dados

        try {
            // Prepara e executa a consulta SQL
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.rs = conexao.pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, cria objetos Status_Curso e os adiciona à lista
            while (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);
                Status_Curso status_Curso = new Status_Curso(id, nome);
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

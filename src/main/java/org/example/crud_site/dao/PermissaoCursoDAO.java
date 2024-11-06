package org.example.crud_site.dao;

// Importando a classe Permissao_Curso para manipular permissões de cursos
import org.example.crud_site.model.Curso;
import org.example.crud_site.model.PermissaoCurso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe Permissao_CursoDAO para manipulação dos dados de permissão de cursos no banco de dados
public class PermissaoCursoDAO {

    // Objeto de conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão com o banco de dados
    public PermissaoCursoDAO() {
        conexao = new Conexao();
    }

    // Método para autorizar a permissão de um curso
    public boolean autorizarPermissao(UUID id_Curso) {

        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE Permissao_Curso SET permissao = TRUE WHERE id_curso = ?")){

            // Define o valor do parâmetro na instrução SQL
            pstmt.setObject(1, id_Curso);

            // Executa o comando SQL e retorna verdadeiro se a atualização for bem-sucedida
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna falso caso ocorra uma exceção
            return false;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para negar a permissão de um curso
    public boolean negarPermissao(UUID id_Curso) {

        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE Permissao_Curso SET permissao = FALSE WHERE id_curso = ?")){

            // Define o valor do parâmetro na instrução SQL
            pstmt.setObject(1, id_Curso);

            // Executa o comando SQL e retorna verdadeiro se a atualização for bem-sucedida
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna falso caso ocorra uma exceção
            return false;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para listar todas as permissões de cursos
    public List<PermissaoCurso> listarPermissoesCurso() {

        // Conecta ao banco de dados
        conexao.conectar();
        // Cria uma lista vazia de Permissao_Curso
        List<PermissaoCurso> permissoes_Curso = new ArrayList<>();

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM Permissao_Curso")){

            // Executa a instrução SQL e armazena os resultados em um ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Processa cada registro do ResultSet e adiciona à lista
            while (rs.next()) {
                UUID id_Conta = (UUID) rs.getObject(2);
                boolean permissao = rs.getBoolean(3);
                String dt_Solicitacao = rs.getString(4);
                String dt_Autorizacao = rs.getString(5);
                UUID id_Curso = (UUID) rs.getObject(6);
                UUID id_Autorizador = (UUID) rs.getObject(7);

                // Cria um objeto Permissao_Curso e adiciona na lista
                PermissaoCurso permissao_Curso = new PermissaoCurso(id_Autorizador,id_Curso, dt_Autorizacao, dt_Solicitacao, permissao, id_Conta, id_Conta);
                permissoes_Curso.add(permissao_Curso);
            }

            // Retorna a lista de permissões de cursos
            return permissoes_Curso;

        } catch (SQLException e) {
            // Retorna null caso ocorra uma exceção
            return null;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para buscar uma permissão de curso pelo ID, incluindo atributos de Curso
    public Curso buscarPermissaoCursoPorId(UUID id) {

        // Conecta ao banco de dados
        Curso curso = null;
        try {
            conexao.conectar();

            PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM curso WHERE id = ?");
            // Define o valor do parâmetro na instrução SQL
            pstmt.setObject(1, id);

            // Executa a consulta e armazena o resultado em ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Verifica se o ResultSet contém algum registro
            if (rs.next()) {
                UUID idConta = (UUID) rs.getObject(1);
                String nome  = rs.getString(2);
                String descricao = rs.getString(3);
                UUID idCurso = (UUID) rs.getObject(4);
                UUID idAutorizador = (UUID) rs.getObject(5);

                // Cria e retorna um objeto Permissao_Curso com os dados obtidos
                curso = new Curso(idConta, nome, descricao, idCurso, idAutorizador);
            }
            return curso;
        } catch (SQLException e) {
            // Retorna null caso ocorra uma exceção
            return null;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }
}

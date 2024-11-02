package org.example.crud_site.dao;

// Importando a classe Permissao_Curso para manipular permissões de cursos
import org.example.crud_site.model.PermissaoCurso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe Permissao_CursoDAO para manipulação dos dados de permissão de cursos no banco de dados
public class Permissao_CursoDAO {

    // Objeto de conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão com o banco de dados
    public Permissao_CursoDAO() {
        conexao = new Conexao();
    }

    // Método para autorizar a permissão de um curso
    public boolean autorizarPermissao(UUID id_Curso) {

        // Conecta ao banco de dados
        conexao.conectar();
        try {
            // Declara a instrução SQL para alterar o status de permissão de um curso
            String sql = "UPDATE Permissao_Curso SET permissao = TRUE WHERE id_curso = ?";

            // Prepara a instrução SQL
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na instrução SQL
            conexao.pstmt.setObject(1, id_Curso);

            // Executa o comando SQL e retorna verdadeiro se a atualização for bem-sucedida
            return conexao.pstmt.executeUpdate() > 0;

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
        try {
            // Declara a instrução SQL para alterar o status de permissão de um curso
            String sql = "UPDATE Permissao_Curso SET permissao = FALSE WHERE id_curso = ?";

            // Prepara a instrução SQL
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na instrução SQL
            conexao.pstmt.setObject(1, id_Curso);

            // Executa o comando SQL e retorna verdadeiro se a atualização for bem-sucedida
            return conexao.pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna falso caso ocorra uma exceção
            return false;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para listar todas as permissões de cursos
    public List<PermissaoCurso> listarPermissoes_Curso() {

        // Conecta ao banco de dados
        conexao.conectar();

        // Declara a instrução SQL para listar todas as permissões de cursos
        String sql = "SELECT * FROM Permissao_Curso";

        // Cria uma lista vazia de Permissao_Curso
        List<PermissaoCurso> permissoes_Curso = new ArrayList<>();

        try {
            // Prepara a instrução SQL
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Executa a instrução SQL e armazena os resultados em um ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Processa cada registro do ResultSet e adiciona à lista
            while (conexao.rs.next()) {
                UUID id_Conta = (UUID) conexao.rs.getObject(2);
                boolean permissao = conexao.rs.getBoolean(3);
                String dt_Solicitacao = conexao.rs.getString(4);
                String dt_Autorizacao = conexao.rs.getString(5);
                UUID id_Curso = (UUID) conexao.rs.getObject(6);
                UUID id_Autorizador = (UUID) conexao.rs.getObject(7);

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
    public PermissaoCurso buscarPermissao_CursoPorId(UUID id) {

        // Conecta ao banco de dados
        conexao.conectar();

        try {
            // Declara a instrução SQL para buscar uma permissão de curso pelo ID, incluindo atributos do Curso
            String sql = """
                         SELECT pc.id, pc.id_conta, pc.permissao, pc.dt_solicitacao, pc.dt_autorizacao, 
                                pc.id_curso, pc.id_autorizador, c.nome AS nome_curso, c.descricao AS descricao_curso 
                         FROM Permissao_Curso pc
                         JOIN Curso c ON pc.id_curso = c.id
                         WHERE pc.id = ?
                         """;

            // Prepara a instrução SQL
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na instrução SQL
            conexao.pstmt.setObject(1, id);

            // Executa a consulta e armazena o resultado em ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Verifica se o ResultSet contém algum registro
            if (conexao.rs.next()) {
                UUID idConta = (UUID) conexao.rs.getObject("id_conta");
                boolean permissao = conexao.rs.getBoolean("permissao");
                String dtSolicitacao = conexao.rs.getString("dt_solicitacao");
                String dtAutorizacao = conexao.rs.getString("dt_autorizacao");
                UUID idCurso = (UUID) conexao.rs.getObject("id_curso");
                UUID idAutorizador = (UUID) conexao.rs.getObject("id_autorizador");
                String nomeCurso = conexao.rs.getString("nome_curso");
                String descricaoCurso = conexao.rs.getString("descricao_curso");

                // Cria e retorna um objeto Permissao_Curso com os dados obtidos
                return new PermissaoCurso(nomeCurso, descricaoCurso, idConta, idAutorizador, idCurso, dtAutorizacao, dtSolicitacao, permissao, idConta, id);
            }

            // Retorna null se nenhum registro for encontrado
            return null;

        } catch (SQLException e) {
            // Retorna null caso ocorra uma exceção
            return null;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }
}

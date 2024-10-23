package org.example.crud_site.dao;

// Importando a classe Permissao_Curso para usar os seus atributos e métodos.
import org.example.crud_site.model.Permissao_Curso;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de permissões.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Permissao_CursoDAO
public class Permissao_CursoDAO {

    // Objeto que acessa os atributos que gerenciam o banco de dados.
    private Conexao conexao;

    // Construtor atribui a conexao uma nova Conexao() com os atributos da classe Conexao.
    public Permissao_CursoDAO() {
        // Inicializa a conexão com o banco de dados.
        conexao = new Conexao();
    }

    // Método para autorizar a permissão de um curso.
    public boolean autorizarPermissao(UUID id_Curso) {

        // Conecta ao banco de dados.
        conexao.conectar();
        try {

            // Instrução SQL para alterar a permissão de um curso.
            String sql = "UPDATE Permissao_Curso SET permissao = TRUE WHERE id_curso = ?";

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL.
            conexao.pstmt.setObject(1, id_Curso);

            // Retorna se o comando SQL foi executado com sucesso.
            return conexao.pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna false se ocorrer algum erro na execução do comando SQL.
            return false;
        } finally {
            // Desconecta do banco de dados.
            conexao.desconectar();
        }
    }

    // Método para negar a permissão de um curso.
    public boolean negarPermissao(UUID id_Curso) {

        // Conecta ao banco de dados.
        conexao.conectar();
        try {

            // Instrução SQL para alterar a permissão de um curso.
            String sql = "UPDATE Permissao_Curso SET permissao = FALSE WHERE id_curso = ?";

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL.
            conexao.pstmt.setObject(1, id_Curso);

            // Retorna se o comando SQL foi executado com sucesso.
            return conexao.pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna false se ocorrer algum erro na execução do comando SQL.
            return false;
        } finally {
            // Desconecta do banco de dados.
            conexao.desconectar();
        }
    }

    // Método para listar todas as permissões de cursos.
    public List<Permissao_Curso> listarPermissoes_Curso() {

        // Conecta ao banco de dados.
        conexao.conectar();

        // Instrução SQL para listar todas as permissões de cursos.
        String sql = "SELECT * FROM Permissao_Curso";

        // Cria uma lista vazia de Permissao_Curso.
        List<Permissao_Curso> permissoes_Curso = new ArrayList<>();

        try {

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Executa a instrução SQL e armazena os resultados em um ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, adiciona os dados na lista de permissões.
            while (conexao.rs.next()) {

                // Pega os dados de cada registro e cria um objeto Permissao_Curso.
                UUID id_Conta = (UUID) conexao.rs.getObject(2);
                boolean permissao = conexao.rs.getBoolean(3);
                String dt_Solicitacao = conexao.rs.getString(4);
                String dt_Autorizacao = conexao.rs.getString(5);
                UUID id_Curso = (UUID) conexao.rs.getObject(6);
                UUID id_Autorizador = (UUID) conexao.rs.getObject(7);

                // Cria um objeto Permissao_Curso e adiciona na lista.
                Permissao_Curso permissao_Curso = new Permissao_Curso(id_Conta, permissao, dt_Solicitacao, dt_Autorizacao, id_Curso, id_Autorizador);
                permissoes_Curso.add(permissao_Curso);
            }

            // Retorna a lista de permissões.
            return permissoes_Curso;

        } catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;

        } finally {
            // Desconecta do banco de dados.
            conexao.desconectar();
        }
    }
}

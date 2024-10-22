package org.example.crud_site.dao;

// Importando a classe Permissao_Curso para usar os seus atributos e métodos.

import org.example.crud_site.model.Permissao_Curso;

//importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de Permissões.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Permissao_CursoDAO
public class Permissao_CursoDAO {

    // Objeto que acessa os atributos que gerenciam o banco de dados.
    private Conexao conexao;

    //Construtor atribui a conexao uma nova Conexao() com os atribuitos da classe Conexao.
    public Permissao_CursoDAO(){
        // Inicializa a conexão com o banco de dados.
        conexao = new Conexao();
    }

    // Método para autorizar a permissão de um curso.
    public boolean autorizarPermissao(UUID id_curso) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try {

            // Instrução SQL para autorizar um curso no banco.
            conexao.pstmt = conexao.conn.prepareStatement("UPDATE Permissao_Curso SET permissao = TRUE WHERE id_curso = ?");
            // Define o parâmetro id_curso na consulta SQL.
            conexao.pstmt.setObject(1, id_curso);

            // Retorna se o comando SQL foi executado com sucesso.
            return conexao.pstmt.executeUpdate() > 0;
        }catch(SQLException e) {
            // Retorna false se ocorrer algum erro na execução do comando SQL.
            return false;
        }finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para negar a permissão de um curso.
    public boolean negarPermissao(UUID id_curso) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try {
            // Instrução SQL para negar a permissão de um curso no banco.
            conexao.pstmt = conexao.conn.prepareStatement("UPDATE Permissao_Curso SET permissao = FALSE WHERE id_curso = ?");
            // Define o parâmetro id_curso na consulta SQL.
            conexao.pstmt.setObject(1, id_curso);

            // Retorna se o comando SQL foi executado com sucesso.
            return conexao.pstmt.executeUpdate() > 0;
        }catch(SQLException e) {
            // Retorna false se ocorrer algum erro na execução do comando SQL.
            e.printStackTrace();
            return false;
        }finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para listar as permissões de cursos.
    public List<Permissao_Curso> listarPermissoes() {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        // Lista vazia que armazenará as permissões de cursos.
        List<Permissao_Curso> permissoes = new ArrayList<>();
        try {

            // Prepara a instrução SQL para listar as permissões de cursos.
            conexao.pstmt = conexao.conn.prepareStatement("SELECT * FROM permissao_curso");

            // Executa a consulta SQL e armazena os resultados em um ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, cria objetos Permissao_Curso e os adiciona à lista.
            while(conexao.rs.next()) {

                // Pega os dados do ResultSet e cria um objeto Permissao_Curso.
                UUID id_conta = (UUID) conexao.rs.getObject(2);
                boolean permissao =  conexao.rs.getBoolean(3);
                String dt_solicitacao = conexao.rs.getString(4);
                String dt_autorizacao = conexao.rs.getString(5);
                UUID id_curso = (UUID) conexao.rs.getObject(6);
                UUID id_autorizador = (UUID) conexao.rs.getObject(7);

                // Cria o objeto Permissao_Curso com os dados obtidos.
                Permissao_Curso permissaoCurso = new Permissao_Curso(id_conta, permissao, dt_solicitacao, dt_autorizacao, id_curso, id_autorizador);

                // Adiciona o objeto Permissao_Curso à lista de permissões.
                permissoes.add(permissaoCurso);
            }
            // Retorna a lista de permissões de cursos.
            return permissoes;
        }catch(SQLException e){
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }
}

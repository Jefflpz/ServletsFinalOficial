package org.example.crud_site.dao;

// Importando a classe Permissao_Vaga para usar os seus atributos e métodos.
import org.example.crud_site.model.Permissao_Vaga;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de Permissões.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Permissao_VagaDAO
public class Permissao_VagaDAO {

    // Objeto que acessa os atributos que gerenciam o banco de dados.
    private Conexao conexao;

    //Construtor atribui a conexao uma nova Conexao() com os atribuitos da classe Conexao.
    public Permissao_VagaDAO(){
        conexao = new Conexao();
    }

    // Método para autorizar permissão de uma vaga.
    public boolean autorizarPermissao(UUID id_vaga) {

        // Conecta ao banco de dados.
        conexao.conectar();
        try {

            // Instrução SQL para alterar a permissão de uma vaga.
            String sql = "UPDATE permissao_vaga SET permissao = TRUE WHERE id_vaga =?";

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL.
            conexao.pstmt.setObject(1, id_vaga);

            // Retorna se o comando SQL foi executado com sucesso.
            return conexao.pstmt.executeUpdate() > 0;

        } catch(SQLException e) {
            // Retorna false se ocorrer algum erro na execução do comando SQL.
            return false;
        } finally {
            // Desconecta do banco de dados.
            conexao.desconectar();
        }
    }

    // Método para negar permissão de uma vaga.
    public boolean negarPermissao(UUID id_vaga) {

        // Conecta ao banco de dados.
        conexao.conectar();
        try {

            // Instrução SQL para alterar a permissão de uma vaga.
            String sql = "UPDATE permissao_vaga SET permissao = FALSE WHERE id_vaga =?";

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL.
            conexao.pstmt.setObject(1, id_vaga);

            // Retorna se o comando SQL foi executado com sucesso.
            return conexao.pstmt.executeUpdate() > 0;

        } catch(SQLException e) {
            // Retorna false se ocorrer algum erro na execução do comando SQL.
            return false;
        } finally {
            // Desconecta do banco de dados.
            conexao.desconectar();
        }
    }

    // Método para listar todas as permissões de vagas.
    public List<Permissao_Vaga> listarPermissoes() {

        // Conecta ao banco de dados.
        conexao.conectar();

        // Instrução SQL para listar todas as permissões de vagas.
        String sql = "SELECT * FROM permissao_vaga";

        // Cria uma lista vazia de Permissao_Vaga.
        List<Permissao_Vaga> permissoes = new ArrayList<>();

        try {

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Executa a instrução SQL e armazena os resultados em um ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, adiciona os dados na lista de permissões.
            while(conexao.rs.next()) {

                // Pega os dados de cada registro e cria um objeto Permissao_Vaga.
                UUID id_empresa = (UUID) conexao.rs.getObject(2);
                boolean permissao = conexao.rs.getBoolean(3);
                String dt_solicitacao = conexao.rs.getString(4);
                String dt_autorizacao = conexao.rs.getString(5);
                UUID id_vaga = (UUID) conexao.rs.getObject(6);
                UUID id_autorizador = (UUID) conexao.rs.getObject(7);

                // Cria um objeto Permissao_Vaga e adiciona na lista.
                Permissao_Vaga permissaovaga = new Permissao_Vaga(id_empresa, permissao, dt_solicitacao, dt_autorizacao, id_vaga, id_autorizador);
                permissoes.add(permissaovaga);
            }

            // Retorna a lista de permissões.
            return permissoes;

        } catch(SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;

        } finally {
            // Desconecta do banco de dados.
            conexao.desconectar();
        }
    }
}

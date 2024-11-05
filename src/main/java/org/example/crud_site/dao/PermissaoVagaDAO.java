package org.example.crud_site.dao;

// Importando a classe Permissao_Vaga para usar os seus atributos e métodos.
import org.example.crud_site.model.PermissaoVaga;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de permissões.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Permissao_VagaDAO
public class PermissaoVagaDAO {

    // Objeto que acessa os atributos que gerenciam o banco de dados.
    private Conexao conexao;

    // Construtor atribui a conexao uma nova Conexao() com os atributos da classe Conexao.
    public PermissaoVagaDAO() {
        conexao = new Conexao();
    }

    // Método para autorizar a permissão de uma vaga.
    public boolean autorizarPermissao(UUID id_Vaga) {

        // Conecta ao banco de dados.
        conexao.conectar();
        try {

            // Instrução SQL para alterar a permissão de uma vaga.
            String sql = "UPDATE permissao_vaga SET permissao = TRUE WHERE id_vaga = ?";

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL.
            conexao.pstmt.setObject(1, id_Vaga);

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

    // Método para negar a permissão de uma vaga.
    public boolean negarPermissao(UUID id_Vaga) {

        // Conecta ao banco de dados.
        conexao.conectar();
        try {

            // Instrução SQL para alterar a permissão de uma vaga.
            String sql = "UPDATE permissao_vaga SET permissao = FALSE WHERE id_vaga = ?";

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL.
            conexao.pstmt.setObject(1, id_Vaga);

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

    // Método para listar todas as permissões de vagas.
    public List<PermissaoVaga> listarPermissoesVaga() {

        // Conecta ao banco de dados.
        conexao.conectar();

        // Instrução SQL para listar todas as permissões de vagas.
        String sql = "SELECT * FROM permissao_vaga";

        // Cria uma lista vazia de Permissao_Vaga.
        List<PermissaoVaga> permissoes_Vaga = new ArrayList<>();

        try {

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Executa a instrução SQL e armazena os resultados em um ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, adiciona os dados na lista de permissões.
            while (conexao.rs.next()) {

                // Pega os dados de cada registro e cria um objeto Permissao_Vaga.
                UUID id_Empresa = (UUID) conexao.rs.getObject(2);
                boolean permissao = conexao.rs.getBoolean(3);
                String dt_Solicitacao = conexao.rs.getString(4);
                String dt_Autorizacao = conexao.rs.getString(5);
                UUID id_Vaga = (UUID) conexao.rs.getObject(6);
                UUID id_Autorizador = (UUID) conexao.rs.getObject(7);

                // Cria um objeto Permissao_Vaga e adiciona na lista.
                PermissaoVaga permissao_Vaga = new PermissaoVaga(null, id_Empresa, permissao, dt_Solicitacao, dt_Autorizacao, id_Vaga, id_Autorizador);
                permissoes_Vaga.add(permissao_Vaga);
            }

            // Retorna a lista de permissões.
            return permissoes_Vaga;

        } catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;

        } finally {
            // Desconecta do banco de dados.
            conexao.desconectar();
        }
    }
    // Método para buscar uma permissão de vaga pelo ID, incluindo atributos de Vaga
    public PermissaoVaga buscarPermissaoVagaPorId(UUID id) {

        // Conecta ao banco de dados
        conexao.conectar();

        try {
            // Declara a instrução SQL para buscar uma permissão de vaga pelo ID, incluindo atributos da Vaga
            String sql = """
                     SELECT pv.id, pv.id_empresa, pv.permissao, pv.dt_solicitacao, pv.dt_autorizacao,
                            pv.id_vaga, pv.id_autorizador, v.nome AS nome_vaga, v.descricao AS descricao_vaga
                     FROM Permissao_Vaga pv
                     JOIN Vaga v ON pv.id_vaga = v.id
                     WHERE pv.id = ?
                     """;

            // Prepara a instrução SQL
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na instrução SQL
            conexao.pstmt.setObject(1, id);

            // Executa a consulta e armazena o resultado em ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Verifica se o ResultSet contém algum registro
            if (conexao.rs.next()) {
                UUID idEmpresa = (UUID) conexao.rs.getObject("id_empresa");
                boolean permissao = conexao.rs.getBoolean("permissao");
                String dtSolicitacao = conexao.rs.getString("dt_solicitacao");
                String dtAutorizacao = conexao.rs.getString("dt_autorizacao");
                UUID idVaga = (UUID) conexao.rs.getObject("id_vaga");
                UUID idAutorizador = (UUID) conexao.rs.getObject("id_autorizador");
                String nomeVaga = conexao.rs.getString("nome_vaga");
                String descricaoVaga = conexao.rs.getString("descricao_vaga");

                // Cria e retorna um objeto Permissao_Vaga com os dados obtidos
                return new PermissaoVaga(nomeVaga, descricaoVaga, idEmpresa, id, idEmpresa, permissao, dtSolicitacao, dtAutorizacao, idVaga, idAutorizador);
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

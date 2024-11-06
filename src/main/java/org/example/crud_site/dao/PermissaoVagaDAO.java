package org.example.crud_site.dao;

// Importando a classe Permissao_Vaga para usar os seus atributos e métodos.
import org.example.crud_site.model.PermissaoVaga;
import org.example.crud_site.model.Vaga;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE permissao_vaga SET permissao = TRUE WHERE id_vaga = ?")) {

            // Define os valores dos parâmetros na consulta SQL.
            pstmt.setObject(1, id_Vaga);

            // Retorna se o comando SQL foi executado com sucesso.
            return pstmt.executeUpdate() > 0;

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
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE permissao_vaga SET permissao = FALSE WHERE id_vaga = ?")){

            // Define os valores dos parâmetros na consulta SQL.
            pstmt.setObject(1, id_Vaga);

            // Retorna se o comando SQL foi executado com sucesso.
            return pstmt.executeUpdate() > 0;

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
        // Cria uma lista vazia de Permissao_Vaga.
        List<PermissaoVaga> permissoes_Vaga = new ArrayList<>();

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM permissao_vaga")){

            // Prepara a instrução SQL para ser executada.


            // Executa a instrução SQL e armazena os resultados em um ResultSet.
            ResultSet rs = pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, adiciona os dados na lista de permissões.
            while (rs.next()) {

                // Pega os dados de cada registro e cria um objeto Permissao_Vaga.
                UUID id = (UUID) rs.getObject(1);
                UUID id_Empresa = (UUID) rs.getObject(2);
                boolean permissao = rs.getBoolean(3);
                String dt_Solicitacao = rs.getString(4);
                String dt_Autorizacao = rs.getString(5);
                UUID id_Vaga = (UUID) rs.getObject(6);
                UUID id_Autorizador = (UUID) rs.getObject(7);

                // Cria um objeto Permissao_Vaga e adiciona na lista.
                PermissaoVaga permissao_Vaga = new PermissaoVaga(id, id_Empresa, permissao, dt_Solicitacao, dt_Autorizacao, id_Vaga, id_Autorizador);
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
    public Vaga buscarPermissaoVagaPorId(UUID id) {

        // Conecta ao banco de dados
        conexao.conectar();
        Vaga vaga = null;
        try
        {
            // Cria a instrução SQL para buscar uma permissão de vaga pelo ID
            PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM vaga WHERE id = ?");
            // Define o valor do parâmetro na instrução SQL
            pstmt.setObject(1, id);

            // Executa a consulta e armazena o resultado em ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Verifica se o ResultSet contém algum registro
            if (rs.next()) {
                UUID idId = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                UUID tipo_vaga = (UUID) rs.getObject(4);
                UUID id_Empresa = (UUID) rs.getObject(5);


                // Cria e retorna um objeto Permissao_Vaga com os dados obtidos
                vaga = new Vaga(idId, tipo_vaga, descricao, nome, id_Empresa);
            }
            return vaga;
        } catch (SQLException e) {
            // Retorna null caso ocorra uma exceção
            return null;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

}

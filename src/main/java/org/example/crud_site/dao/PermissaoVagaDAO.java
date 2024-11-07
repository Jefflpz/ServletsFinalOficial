package org.example.crud_site.dao;

// Importando a classe PermissaoVaga e Vaga para manipulação dos dados de permissões de vaga
import org.example.crud_site.model.PermissaoVaga;
import org.example.crud_site.model.Vaga;

// Importa as classes necessárias para manipulação de SQL e listas
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe PermissaoVagaDAO para manipulação das permissões de vaga no banco de dados
public class PermissaoVagaDAO {

    // Objeto para gerenciar a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa uma nova conexão
    public PermissaoVagaDAO() {
        conexao = new Conexao();
    }

    // Método para autorizar a permissão de uma vaga
    public boolean autorizarPermissao(UUID id_Vaga) {
        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE permissao_vaga SET permissao = TRUE WHERE id_vaga = ?")) {

            // Define o valor do parâmetro da consulta SQL
            pstmt.setObject(1, id_Vaga);

            // Retorna verdadeiro se a atualização foi bem-sucedida
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna falso em caso de erro
            return false;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para negar a permissão de uma vaga
    public boolean negarPermissao(UUID id_Vaga) {
        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE permissao_vaga SET permissao = FALSE WHERE id_vaga = ?")) {

            // Define o valor do parâmetro da consulta SQL
            pstmt.setObject(1, id_Vaga);

            // Retorna verdadeiro se a atualização foi bem-sucedida
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna falso em caso de erro
            return false;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para listar todas as permissões de vaga
    public List<PermissaoVaga> listarPermissoesVaga() {
        // Conecta ao banco de dados
        conexao.conectar();
        // Cria uma lista para armazenar as permissões de vaga
        List<PermissaoVaga> permissoes_Vaga = new ArrayList<>();

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM permissao_vaga")) {

            // Executa a consulta SQL e armazena o resultado no ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Itera sobre os registros do ResultSet e os adiciona na lista
            while (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                UUID id_Empresa = (UUID) rs.getObject(2);
                boolean permissao = rs.getBoolean(3);
                String dt_Solicitacao = rs.getString(4);
                String dt_Autorizacao = rs.getString(5);
                UUID id_Vaga = (UUID) rs.getObject(6);
                UUID id_Autorizador = (UUID) rs.getObject(7);

                // Cria um objeto PermissaoVaga e o adiciona à lista
                PermissaoVaga permissao_Vaga = new PermissaoVaga(id, id_Empresa, permissao, dt_Solicitacao, dt_Autorizacao, id_Vaga, id_Autorizador);
                permissoes_Vaga.add(permissao_Vaga);
            }

            // Retorna a lista de permissões de vaga
            return permissoes_Vaga;

        } catch (SQLException e) {
            // Retorna null em caso de erro
            return null;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para buscar uma permissão de vaga pelo ID, incluindo atributos de Vaga
    public Vaga buscarPermissaoVagaPorId(UUID id) {
        // Conecta ao banco de dados
        conexao.conectar();
        Vaga vaga = null;
        try {
            // Prepara a consulta SQL para buscar a vaga pelo ID
            PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM vaga WHERE id = ?");
            pstmt.setObject(1, id);

            // Executa a consulta e armazena o resultado no ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Verifica se há registros no ResultSet
            if (rs.next()) {
                UUID idId = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                UUID tipo_vaga = (UUID) rs.getObject(4);
                UUID id_Empresa = (UUID) rs.getObject(5);

                // Cria um objeto Vaga com os dados obtidos e o retorna
                vaga = new Vaga(idId, tipo_vaga, descricao, nome, id_Empresa);
            }
            return vaga;

        } catch (SQLException e) {
            // Retorna null em caso de erro
            return null;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }
}

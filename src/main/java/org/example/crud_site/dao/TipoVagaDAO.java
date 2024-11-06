package org.example.crud_site.dao;

// Importando a classe Tipo_Vaga para manipular os dados relacionados.
import org.example.crud_site.model.TipoVaga;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista dos tipos de vaga.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Tipo_VagaDAO
public class TipoVagaDAO {

    // Objeto responsável por gerenciar a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão com uma nova instância de Conexao().
    public TipoVagaDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo tipo de vaga
    public boolean inserirTipo_Vaga(String tipo_vaga) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO tipo_vaga (nome) VALUES (?)")){


            // Define os valores dos parâmetros de consulta
            pstmt.setString(1, tipo_vaga);

            // Executa a instrução SQL
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para atualizar um tipo de vaga
    public boolean alterarTipoVaga(UUID uuid, String novoTipoVaga) {
        conexao.conectar();
        try( PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE tipo_vaga SET nome = ? WHERE id = ?")) {


            // Define os valores dos parâmetros da consulta SQL
            pstmt.setString(1, novoTipoVaga);
            pstmt.setObject(2, uuid);

            // Executa a instrução SQL
            int rows = pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o registro.", e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para excluir um tipo de vaga
    public boolean excluirTipo_Vaga(String nomeTipo_Vaga) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM tipo_vaga WHERE nome = ?")){


            // Define o valor do parâmetro na consulta SQL
            pstmt.setString(1, nomeTipo_Vaga);

            // Executa a instrução SQL
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um tipo de vaga pelo nome
    public TipoVaga buscarTipo_Vaga(String nomeTipo_Vaga) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM tipo_vaga WHERE nome = ?")){
            // Instrução SQL para buscar um tipo de vaga na tabela tipo_vaga

            pstmt.setString(1, nomeTipo_Vaga);

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                // Cria um objeto Tipo_Vaga com os dados do ResultSet
                return new TipoVaga(id, nome);
            }
            // Retorna null se não encontrar o tipo de vaga
            return null;
        } catch (SQLException e) {
            return null; // Retorna null caso ocorra algum erro
        } finally {
            conexao.desconectar();
        }
    }

    // Método para listar todos os tipos de vaga na tabela tipo_vaga
    public List<TipoVaga> listarTipo_Vaga() {

        List<TipoVaga> tipos_vagas = new ArrayList<>();
        conexao.conectar();

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM tipo_vaga")){

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Obtém os dados do ResultSet e armazena na lista de tipos de vaga
            while (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                TipoVaga tipo_vaga = new TipoVaga(id, nome);
                // Adiciona o objeto Tipo_Vaga na lista
                tipos_vagas.add(tipo_vaga);
            }
        } catch (SQLException e) {
            return null; // Retorna null caso ocorra algum erro
        } finally {
            conexao.desconectar();
        }

        // Retorna a lista de tipos de vaga
        return tipos_vagas;
    }
}

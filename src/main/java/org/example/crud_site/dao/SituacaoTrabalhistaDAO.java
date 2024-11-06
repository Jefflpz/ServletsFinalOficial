package org.example.crud_site.dao;

// Importando a classe Situacao_Trabalhista para manipular os dados relacionados.
import org.example.crud_site.model.SituacaoTrabalhista;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista das situações trabalhistas.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe que gerencia as operações de banco de dados relacionadas à tabela "Situacao_Trabalhista".
public class SituacaoTrabalhistaDAO {

    // Atributo para gerenciar a conexão com o banco de dados.
    private Conexao conexao;

    // Construtor que inicializa a conexão com uma nova instância de Conexao().
    public SituacaoTrabalhistaDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo registro na tabela "Situacao_Trabalhista".
    public boolean inserirSituacaoTrabalhista(String situacao_trabalhista) {
        // Estabelece conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO situacao_trabalhista (nome) VALUES (?)")){
            // Define o valor do nome da situação trabalhista.
            pstmt.setString(1, situacao_trabalhista);

            // Executa a instrução SQL e retorna true se a inserção foi bem-sucedida.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Lança uma exceção se ocorrer algum erro.
            throw new RuntimeException(e);
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de uma situação trabalhista.
    public boolean alterarNomeSituacaoTrabalhista(String situacao_trabalhista, String nome) {
        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE situacao_trabalhista SET nome = ? WHERE nome = ?")){

            // Define os parâmetros da consulta: o novo nome e o nome atual.
            pstmt.setString(1, nome);
            pstmt.setString(2, situacao_trabalhista);

            // Executa a consulta SQL.
            int rows = pstmt.executeUpdate();

            // Verifica se algum registro foi alterado. Se não, lança uma exceção.
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        } catch (SQLException e) {
            // Lança uma exceção se ocorrer algum erro.
            throw new RuntimeException("Erro ao atualizar o registro.", e);
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para excluir uma situação trabalhista da tabela.
    public boolean excluirSituacaoTrabalhista(String nomeSituacao_Trabalhista) {
        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM situacao_trabalhista WHERE nome = ?")) {

            // Define o valor do nome da situação trabalhista a ser excluída.
            pstmt.setString(1, nomeSituacao_Trabalhista);

            // Executa a instrução SQL de exclusão.
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            // Lança uma exceção em caso de erro.
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para buscar uma situação trabalhista pelo nome.
    public SituacaoTrabalhista buscarSituacaoTrabalhista(String nomeSituacao_Trabalhista) {
        // Objeto para armazenar a situação trabalhista encontrada.
        SituacaoTrabalhista situacao_trabalhista;

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM situacao_trabalhista WHERE nome = ?")) {
            // Define o valor do nome da situação trabalhista a ser excluída.
            pstmt.setString(1, nomeSituacao_Trabalhista);

            // Executa a consulta e armazena o resultado.
            ResultSet rs = pstmt.executeQuery();

            // Verifica se a consulta retornou algum resultado.
            if (rs.next()) {
                // Obtém os dados da situação trabalhista do ResultSet.
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);

                // Cria um novo objeto Situacao_Trabalhista com os dados retornados.
                return new SituacaoTrabalhista(id, nome);
            }
            // Retorna null se não houver nenhum resultado.
            return null;

        } catch (SQLException e) {
            // Retorna null em caso de erro.
            return null;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para listar todas as situações trabalhistas.
    public List<SituacaoTrabalhista> listarSituacaoTrabalhista() {
        // Instrução SQL para listar todas as situações trabalhistas.
        String sql = "SELECT * FROM situacao_trabalhista";

        // Cria uma lista vazia de situações trabalhistas.
        List<SituacaoTrabalhista> situacoes_trabalhistas = new ArrayList<>();

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM situacao_trabalhista")){
            // Executa a consulta e armazena o resultado.
            ResultSet rs = pstmt.executeQuery();

            // Percorre os resultados e armazena cada situação trabalhista na lista.
            while (rs.next()) {
                // Obtém os dados do ResultSet.
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);

                // Cria um novo objeto Situacao_Trabalhista e o adiciona à lista.
                SituacaoTrabalhista situacao_trabalhista = new SituacaoTrabalhista(id, nome);
                situacoes_trabalhistas.add(situacao_trabalhista);
            }
        } catch (SQLException e) {
            // Retorna null em caso de erro.
            return null;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }

        // Retorna a lista de situações trabalhistas.
        return situacoes_trabalhistas;
    }
}

package org.example.crud_site.dao;

// Importando a classe Setor para utilizar seus atributos e métodos.
import org.example.crud_site.model.Setor;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de setores.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;
import java.util.function.Predicate;

// Classe SetorDAO
public class SetorDAO {

    // Objeto que acessa os atributos que gerenciam a conexão com o banco de dados.
    private Conexao conexao;

    // Construtor que inicializa a conexão com o banco de dados.
    public SetorDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um registro na tabela Setor.
    public boolean inserir(String setor) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO Setor (nome) VALUES (?)")){

            // Define os valores dos parâmetros da consulta.
            pstmt.setString(1, setor);

            // Executa a instrução SQL e retorna true se bem-sucedido.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Retorna false se ocorrer algum erro.
            return false;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de um setor existente na tabela Setor.
    public boolean alterarNomeSetor(UUID uuid, String novoSetor) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE Setor SET nome = ? WHERE id = ?")){

            // Define os valores dos parâmetros da consulta SQL.
            pstmt.setString(1, novoSetor);
            pstmt.setObject(2, uuid);

            // Executa a instrução SQL e verifica se algum registro foi alterado.
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }

            // Retorna true se a alteração foi bem-sucedida.
            return rows > 0;
        } catch (SQLException e) {
            // Retorna false em caso de erro.
            return false;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para excluir um setor da tabela Setor.
    public boolean excluirSetor(String nomeSetor) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM Setor WHERE nome = ?")){

            // Define o valor do parâmetro na consulta SQL.
            pstmt.setString(1, nomeSetor);

            // Executa a instrução SQL.
            pstmt.execute();
            // Retorna true se a exclusão foi bem-sucedida.
            return true;
        } catch (SQLException e) {
            // Lança uma exceção em caso de erro.
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para buscar um setor pelo nome na tabela Setor.
    public Setor buscarSetor(String nomeSetor) {

        // Cria um objeto Setor para armazenar os dados obtidos da consulta.
        Setor setor;

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM Setor WHERE nome = ?")){
            // Define o valor do parâmetro na consulta SQL.
            pstmt.setString(1, nomeSetor);

            // Executa a consulta e armazena o resultado no ResultSet.
            ResultSet rs = pstmt.executeQuery();

            // Verifica se o ResultSet contém dados.
            if (rs.next()) {
                // Obtém o nome do setor do ResultSet.
                String nome = rs.getString(1);

                // Cria um novo objeto Setor com os dados obtidos.
                return new Setor(nome);
            }
            // Retorna null se não houver nenhum setor com o nome informado.
            return null;
        } catch (SQLException e) {
            // Retorna null em caso de erro.
            return null;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }

        // Retorna o objeto Setor encontrado.

    }

    // Método para listar todos os setores da tabela Setor.
    public List<Setor> listarSetores() {


        // Cria uma lista vazia para armazenar os setores.
        List<Setor> setores = new ArrayList<>();

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM Setor")) {

            // Prepara e executa a consulta SQL.
            ResultSet rs = pstmt.executeQuery();

            // Percorre o ResultSet e armazena os dados dos setores na lista.
            while (rs.next()) {

                // Pega os dados do setor do ResultSet.
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);

                // Cria um objeto Setor com os dados obtidos.
                Setor setor = new Setor(id, nome);

                // Adiciona o setor à lista.
                setores.add(setor);
            }
        } catch (SQLException e) {
            // Retorna null em caso de erro.
            return null;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }

        // Retorna a lista de setores.
        return setores;
    }
}

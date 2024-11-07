package org.example.crud_site.dao;

// Importando a classe Setor para manipulação dos dados do setor
import org.example.crud_site.model.Setor;

// Importando classes para manipulação de SQL e listas
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Classe SetorDAO para operações de CRUD na tabela Setor
public class SetorDAO {

    // Objeto que gerencia a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão
    public SetorDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo setor na tabela Setor
    public boolean inserir(String setor) {
        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO Setor (nome) VALUES (?)")) {

            // Define o valor do parâmetro da consulta SQL
            pstmt.setString(1, setor);

            // Retorna true se a inserção foi bem-sucedida
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Retorna false em caso de erro
            return false;

        } finally {
            // Desconecta do banco de dados
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de um setor existente na tabela Setor
    public boolean alterarNomeSetor(UUID uuid, String novoSetor) {
        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE Setor SET nome = ? WHERE id = ?")) {

            // Define os valores dos parâmetros da consulta SQL
            pstmt.setString(1, novoSetor);
            pstmt.setObject(2, uuid);

            // Verifica se o setor foi atualizado
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }

            return rows > 0;

        } catch (SQLException e) {
            return false;

        } finally {
            conexao.desconectar();
        }
    }

    // Método para excluir um setor da tabela Setor
    public boolean excluirSetor(String nomeSetor) {
        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM Setor WHERE nome = ?")) {

            // Define o parâmetro da consulta SQL
            pstmt.setObject(1, nomeSetor);

            // Executa a exclusão e retorna true se bem-sucedido
            pstmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o registro.", e);

        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um setor pelo nome
    public Setor buscarSetor(String nomeSetor) {
        // Objeto Setor para armazenar o resultado
        Setor setor;

        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM Setor WHERE nome = ?")) {

            // Define o parâmetro da consulta SQL
            pstmt.setString(1, nomeSetor);

            // Executa a consulta
            ResultSet rs = pstmt.executeQuery();

            // Verifica se o setor existe
            if (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);

                return new Setor(id, nome);
            }
            return null;

        } catch (SQLException e) {
            return null;

        } finally {
            conexao.desconectar();
        }
    }

    // Método para listar todos os setores
    public List<Setor> listarSetores() {
        List<Setor> setores = new ArrayList<>();

        // Conecta ao banco de dados
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM Setor")) {

            // Executa a consulta SQL
            ResultSet rs = pstmt.executeQuery();

            // Adiciona cada setor à lista
            while (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);

                Setor setor = new Setor(id, nome);
                setores.add(setor);
            }
        } catch (SQLException e) {
            return null;

        } finally {
            conexao.desconectar();
        }

        return setores;
    }
}

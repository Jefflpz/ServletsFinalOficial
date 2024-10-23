package org.example.crud_site.dao;

// Importando a classe Setor para utilizar seus atributos e métodos.
import org.example.crud_site.model.Setor;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de setores.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe SetorDAO
public class SetorDAO {

    // Objeto que acessa os atributos que gerenciam a conexão com o banco de dados.
    private Conexao conexao;

    // Construtor que inicializa a conexão com o banco de dados.
    public SetorDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um registro na tabela Setor.
    public boolean inserir(Setor setor) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try {
            // Instrução SQL para inserir um novo setor no banco de dados.
            String sql = "INSERT INTO Setor (nome) VALUES (?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta.
            conexao.pstmt.setString(1, setor.getNome());

            // Executa a instrução SQL e retorna true se bem-sucedido.
            return conexao.pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Retorna false se ocorrer algum erro.
            return false;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de um setor existente na tabela Setor.
    public boolean alterarNome(Setor setor, String nome) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try {
            // Instrução SQL para atualizar o nome de um setor.
            String sql = "UPDATE Setor SET nome = ? WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta SQL.
            conexao.pstmt.setString(1, nome);
            conexao.pstmt.setString(2, setor.getNome());

            // Executa a instrução SQL e verifica se algum registro foi alterado.
            int rows = conexao.pstmt.executeUpdate();
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
    public void excluirSetor(String nomeSetor) {

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try {
            // Instrução SQL para excluir um setor da tabela Setor.
            String sql = "DELETE FROM Setor WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na consulta SQL.
            conexao.pstmt.setString(1, nomeSetor);

            // Executa a instrução SQL.
            conexao.pstmt.execute();
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
        try {
            // Instrução SQL para buscar um setor pelo nome.
            String sql = "SELECT * FROM Setor WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, nomeSetor);

            // Executa a consulta e armazena o resultado no ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Verifica se o ResultSet contém dados.
            if (conexao.rs.next()) {
                // Obtém o nome do setor do ResultSet.
                String nome = conexao.rs.getString(1);

                // Cria um novo objeto Setor com os dados obtidos.
                setor = new Setor(nome);
            } else {
                return null;
            }
        } catch (SQLException e) {
            // Retorna null em caso de erro.
            return null;
        } finally {
            // Fecha a conexão com o banco de dados.
            conexao.desconectar();
        }

        // Retorna o objeto Setor encontrado.
        return setor;
    }

    // Método para listar todos os setores da tabela Setor.
    public List<Setor> listarSetores() {

        // Instrução SQL para listar todos os setores.
        String sql = "SELECT * FROM Setor";

        // Cria uma lista vazia para armazenar os setores.
        List<Setor> setores = new ArrayList<>();

        // Estabelece a conexão com o banco de dados.
        conexao.conectar();
        try {

            // Prepara e executa a consulta SQL.
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.rs = conexao.pstmt.executeQuery();

            // Percorre o ResultSet e armazena os dados dos setores na lista.
            while (conexao.rs.next()) {

                // Pega os dados do setor do ResultSet.
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);

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

package org.example.crud_site.dao;

// Importando a classe Tipo_Vaga para manipular os dados relacionados.
import org.example.crud_site.model.Tipo_Vaga;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista dos tipos de vaga.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Tipo_VagaDAO
public class Tipo_VagaDAO {

    // Objeto responsável por gerenciar a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão com uma nova instância de Conexao().
    public Tipo_VagaDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo tipo de vaga
    public boolean insert(Tipo_Vaga tipo_vaga) {
        conexao.conectar();
        try {
            // Instrução SQL para inserir registro na tabela tipo_vaga
            String sql = "INSERT INTO tipo_vaga (nome) VALUES (?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros de consulta
            conexao.pstmt.setString(1, tipo_vaga.getNome());

            // Executa a instrução SQL
            return conexao.pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para atualizar um tipo de vaga
    public boolean atualizarTipo_Vaga(Tipo_Vaga tipo_vaga, String nome) {
        conexao.conectar();
        try {
            // Instrução SQL para atualizar o nome do tipo de vaga
            String sql = "UPDATE tipo_vaga SET nome = ? WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta SQL
            conexao.pstmt.setString(1, nome);
            conexao.pstmt.setString(2, tipo_vaga.getNome());

            // Executa a instrução SQL
            int rows = conexao.pstmt.executeUpdate();

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
    public void excluirTipo_Vaga(String nomeTipo_Vaga) {
        conexao.conectar();
        try {
            // Instrução SQL para excluir um tipo de vaga na tabela tipo_vaga
            String sql = "DELETE FROM tipo_vaga WHERE nome=?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na consulta SQL
            conexao.pstmt.setString(1, nomeTipo_Vaga);

            // Executa a instrução SQL
            conexao.pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um tipo de vaga pelo nome
    public Tipo_Vaga buscarTipo_Vaga(String nomeTipo_Vaga) {
        conexao.conectar();
        try {
            // Instrução SQL para buscar um tipo de vaga na tabela tipo_vaga
            String sql = "SELECT * FROM tipo_vaga WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, nomeTipo_Vaga);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            if (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);
                // Cria um objeto Tipo_Vaga com os dados do ResultSet
                return new Tipo_Vaga(id, nome);
            } else {
                return null; // Retorna null se não encontrar o tipo de vaga
            }
        } catch (SQLException e) {
            return null; // Retorna null caso ocorra algum erro
        } finally {
            conexao.desconectar();
        }
    }

    // Método para listar todos os tipos de vaga na tabela tipo_vaga
    public List<Tipo_Vaga> listarTipo_Vaga() {
        // Instrução SQL para listar todos os tipos de vaga
        String sql = "SELECT * FROM tipo_vaga";
        List<Tipo_Vaga> tipos_vagas = new ArrayList<>();
        conexao.conectar();

        try {
            // Prepara a instrução SQL para executar a consulta
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtém os dados do ResultSet e armazena na lista de tipos de vaga
            while (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);
                Tipo_Vaga tipo_vaga = new Tipo_Vaga(id, nome);
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

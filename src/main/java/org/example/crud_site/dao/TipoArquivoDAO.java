package org.example.crud_site.dao;

// Importando a classe Tipo_Arquivo para manipular os dados relacionados.
import org.example.crud_site.model.Tipo_Arquivo;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de tipos de arquivos.
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

// Classe Tipo_ArquivoDAO
public class TipoArquivoDAO {

    // Objeto responsável por gerenciar a conexão com o banco de dados
    private Conexao conexao;

    // Construtor que inicializa a conexão com uma nova instância de Conexao().
    public TipoArquivoDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo tipo de arquivo
    public boolean inserirTipoArquivo(String tipo_arquivo) {
        conexao.conectar();
        try {
            // Instrução SQL para inserir registro na tabela tipo_arquivo
            String sql = "INSERT INTO tipo_arquivo (nome) VALUES (?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros de consulta
            conexao.pstmt.setString(1, tipo_arquivo);

            // Executa a instrução SQL
            return conexao.pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de um tipo de arquivo
    public boolean alterarNomeTipoArquivo(Tipo_Arquivo tipo_arquivo, String nome) {
        conexao.conectar();
        try {
            String sql = "UPDATE tipo_arquivo SET nome = ? WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta SQL
            conexao.pstmt.setString(1, nome);
            conexao.pstmt.setString(2, tipo_arquivo.getNome());

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

    // Método para excluir um tipo de arquivo
    public boolean excluirTipoArquivo(String nomeTipo_Arquivo) {
        conexao.conectar();
        try {
            // Instrução SQL para excluir um tipo de arquivo na tabela tipo_arquivo
            String sql = "DELETE FROM tipo_arquivo WHERE nome=?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define o valor do parâmetro na consulta SQL
            conexao.pstmt.setString(1, nomeTipo_Arquivo);

            // Executa a instrução SQL
            conexao.pstmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um tipo de arquivo pelo nome
    public Tipo_Arquivo buscarTipoArquivo(String nomeTipo_Arquivo) {
        conexao.conectar();
        try {
            // Instrução SQL para buscar um tipo de arquivo na tabela tipo_arquivo
            String sql = "SELECT * FROM tipo_arquivo WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, nomeTipo_Arquivo);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            if (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);
                // Cria um objeto Tipo_Arquivo com os dados do ResultSet
                return new Tipo_Arquivo(id, nome);
            }
            // Retorna null se não encontrar o tipo de arquivo
            return null;

        } catch (SQLException e) {
            return null; // Retorna null caso ocorra algum erro
        } finally {
            conexao.desconectar();
        }
    }

    // Método para listar todos os tipos de arquivo na tabela tipo_arquivo
    public List<Tipo_Arquivo> listarTipoArquivo() {
        // Instrução SQL para listar todos os tipos de arquivo
        String sql = "SELECT * FROM tipo_arquivo";
        List<Tipo_Arquivo> tipos_arquivos = new ArrayList<>();
        conexao.conectar();

        try {
            // Prepara a instrução SQL para executar a consulta
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtém os dados do ResultSet e armazena na lista de tipos de arquivo
            while (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);
                Tipo_Arquivo tipo_arquivo = new Tipo_Arquivo(id, nome);
                // Adiciona o objeto Tipo_Arquivo na lista
                tipos_arquivos.add(tipo_arquivo);
            }
        } catch (SQLException e) {
            return null; // Retorna null caso ocorra algum erro
        } finally {
            conexao.desconectar();
        }

        // Retorna a lista de tipos de arquivo
        return tipos_arquivos;
    }
}

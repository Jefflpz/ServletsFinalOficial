package org.example.crud_site.dao;

// Importando a classe Tipo_Arquivo para manipular os dados relacionados.
import org.example.crud_site.model.TipoArquivo;

// Importando a classe SQLException para tratar os erros de SQL.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO tipo_arquivo (nome) VALUES (?)")){
            // Define os valores dos parâmetros de consulta
            pstmt.setString(1, tipo_arquivo);

            // Executa a instrução SQL
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para alterar o nome de um tipo de arquivo
    public boolean alterarNomeTipoArquivo(UUID uuid, String novoTipoArquivo) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE tipo_arquivo SET nome = ? WHERE id = ?")){
            // Define os valores dos parâmetros da consulta SQL
            pstmt.setString(1, novoTipoArquivo);
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

    // Método para excluir um tipo de arquivo
    public boolean excluirTipoArquivo(String nomeTipo_Arquivo) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM tipo_arquivo WHERE nome = ?")){
            // Define o valor do parâmetro na consulta SQL
            pstmt.setString(1, nomeTipo_Arquivo);

            // Executa a instrução SQL
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o registro.", e);
        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um tipo de arquivo pelo nome
    public TipoArquivo buscarTipoArquivo(String nomeTipo_Arquivo) {
        conexao.conectar();
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM tipo_arquivo WHERE nome = ?")){

            pstmt.setString(1, nomeTipo_Arquivo);

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                // Cria um objeto Tipo_Arquivo com os dados do ResultSet
                return new TipoArquivo(id, nome);
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
    public List<TipoArquivo> listarTipoArquivo() {

        List<TipoArquivo> tipos_arquivos = new ArrayList<>();
        conexao.conectar();

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM tipo_arquivo")){

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Obtém os dados do ResultSet e armazena na lista de tipos de arquivo
            while (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String nome = rs.getString(2);
                TipoArquivo tipo_arquivo = new TipoArquivo(id, nome);
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

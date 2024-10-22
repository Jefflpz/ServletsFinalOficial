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

public class Tipo_VagaDAO {

    // Objeto responsável por gerenciar a conexão com o banco de dados
    private Conexao conexao;

    //Construtor atribui a conexao uma nova Conexao() com os atribuitos da classe Conexao.
    public Tipo_VagaDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo tipo de vaga
    public boolean insert(Tipo_Vaga tipo_vaga) {

        conexao.conectar();
        try {

            // Instrução SQL para inserir registro na tabela Setor
            String sql = "INSERT INTO tipo_vaga (nome) VALUES (?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmtros de consulta
            conexao.pstmt.setString(1, tipo_vaga.getNome());

            // Executa a instrução SQL
            return conexao.pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexao.desconectar();
        }
    }

    //Metodo para atualizar um tipo de vaga
    public boolean atualizarTipo_Vaga(Tipo_Vaga  tipo_vaga, String nome) {
        conexao.conectar();
        try {
            String sql = "UPDATE tipo_vaga SET nome = ? WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros de consulta SQL
            conexao.pstmt.setString(1, nome);
            conexao.pstmt.setString(2, tipo_vaga.getNome());

            // Executa a instrução SQL.
            int rows = conexao.pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro.
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o registro.", e);
        }finally {
            conexao.desconectar();
        }
    }
    // Método para excluir um tipo de vaga
    public void excluirTipo_Vaga(String nomeTipo_Vaga) {
        conexao.conectar();
        try{
            // Instrução SQL para excluir uma situação_trabalhista na tabela Adm
            String sql = "DELETE FROM tipo_vaga WHERE nome=?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL
            conexao.pstmt.setString(1, nomeTipo_Vaga);

            // executa instrução SQL
            conexao.pstmt.execute();
        }catch (SQLException e){
            throw new RuntimeException("Erro ao excluir o registro.", e);
        }finally {
            conexao.desconectar();
        }
    }
    // Método para procurar um tipo de vaga por nome
    public Tipo_Vaga buscarTipo_Vaga(String nomeTipo_Vaga){
        // Cria um objeto Setor para armazenar os dados do setor e ser o retorno método.

        conexao.conectar();

        try {

            // Instrução SQL para excluir um administrador na tabela Adm
            String sql = "SELECT * FROM tipo_vaga WHERE nome = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, nomeTipo_Vaga);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            if (conexao.rs.next()){
                UUID id = (UUID)conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);

                return new Tipo_Vaga(id, nome);
            }else {
                return null;
            }
        }catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    // Método para listar todos os tipos de vaga
    // Método para listar todos os setores na tabela Setor
    public   List<Tipo_Vaga> listarTipo_Vaga() {

        // Instrução SQL para listar todos os administradores na tabela Adm
        String sql = "SELECT * FROM tipo_vaga";

        // Essa linha cria uma lista vazia chamada adms, pronta para armazenar objetos do tipo Adm.
        // Usamos o ArrayList para faciliar a manipulação da lista.
        List<Tipo_Vaga> tipos_vagas = new ArrayList<>();

        conexao.conectar();

        try {

            // Prepara a instrução SQL para executar a consulta.
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            // Armazena o resultado da consulta no objeto ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Obtem os dados do ResultSet e armazena na lista de administradores.
            // Usamos o while para percorrer o ResultSet caso haja mais de um registro.
            while (conexao.rs.next()) {

                // Pega os dados do ResultSet
                UUID id = (UUID) conexao.rs.getObject(1);
                String nome = conexao.rs.getString(2);

                // Cria um objeto Adm com os dados do ResultSet
                Tipo_Vaga tipo_vaga = new Tipo_Vaga(id,nome);


                // Adiciona o objeto Adm na lista de administradores
                tipos_vagas.add(tipo_vaga);
            }
        }catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }

        // Retorna a lista de administradores
        return tipos_vagas;
    }
}

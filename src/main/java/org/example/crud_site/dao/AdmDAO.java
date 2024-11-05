package org.example.crud_site.dao;

// Importando a classe Adm para usar os seus atributos e métodos.

import org.example.crud_site.model.Adm;

//importando a classe SQLException para tratar os erros de SQL.
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de administradores
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

//Classe AdmDAO
public class AdmDAO{

    // objeto que acessa os atributos que gerenciam o banco de dados
    private Conexao conexao;

    //Construtor atribui a conexao uma nova Conexao() com os atribuitos da classe Conexao.
    public AdmDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo registro na tabala Adm
    public boolean inserirAdm(String username, String senha) {

        conexao.conectar();
        try{

            // Instrução SQL para inserir um administrador na tabela Adm.
            String sql = "INSERT INTO adm (username, senha) VALUES (?,?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta
            conexao.pstmt.setString(1, username);
            conexao.pstmt.setString(2, senha);

            //Executa a instrução SQL e se o numero de linhas afetadas for maior que 0 insere um usuário no banco de dados.
            if (conexao.pstmt.executeUpdate() > 0) {

                // Instrução SQL pra inserir um administrador no banco
                conexao.pstmt = conexao.conn.prepareStatement("CREATE USER " + username + " WITH PASSWORD '" + senha + "'"  );
                // Executa a instrução SQL
                conexao.pstmt.executeUpdate();

                //retorna true se não der erro
                return true;
            }
            return false;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conexao.desconectar();
        }
    }

    // Método para alterar a senha de um administrador na tabela Adm
    public boolean alterarSenhaAdm(String novaSenha, UUID id) {
        conexao.conectar();
        try {

            // Instrução SQL para inserir um administrador na tabela Adm.
            String sql = "UPDATE adm SET senha=? WHERE id=?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL
            conexao.pstmt.setString(1, novaSenha);
            conexao.pstmt.setObject(2, id);

            // Executa a instrução SQL.
            int rows = conexao.pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro.
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            // retorna true se a quantidade
            return rows > 0;
        }catch (SQLException e){
            return false;
        }finally {
            conexao.desconectar();
        }
    }

    // Método para alterar o login de um administrador na tabela Adm
    public boolean alterarLoginAdm(String novoUsername, UUID id) {
        conexao.conectar();
        try {

            // Instrução SQL para inserir um administrador na tabela Adm.
            String sql = "UPDATE adm SET username=? WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL
            conexao.pstmt.setString(1, novoUsername);
            conexao.pstmt.setObject(2, id);

            // Executa a instrução SQL.
            int rows = conexao.pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro.
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        }catch (SQLException e){
            return false;
//            throw new RuntimeException("Erro ao atualizar o registro.", e);

        }finally {
            conexao.desconectar();
        }
    }


    // Método para excluir um administrador na tabela Adm
    public boolean excluirAdm(UUID id) {
        conexao.conectar();
        try {
            // Primeiro, obtemos o nome do usuário associado ao administrador que será excluído
            String getUserSql = "SELECT username FROM adm WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(getUserSql);
            conexao.pstmt.setObject(1, id);
            conexao.rs = conexao.pstmt.executeQuery();

            String username = null;
            if (conexao.rs.next()) {
                username = conexao.rs.getString("username");
            }

            // Verifica se o usuário foi encontrado
            if (username == null) {
                throw new RuntimeException("Administrador não encontrado com o ID fornecido.");
            }

            // Exclui o administrador da tabela `adm`
            String deleteSql = "DELETE FROM adm WHERE id = ?";
            conexao.pstmt = conexao.conn.prepareStatement(deleteSql);
            conexao.pstmt.setObject(1, id);
            int rowsAffected = conexao.pstmt.executeUpdate();

            // Se o administrador foi excluído com sucesso, tenta excluir o usuário do banco
            if (rowsAffected > 0) {
                // Exclui o usuário associado no banco
                String dropUserSql = "DROP USER " + username;
                conexao.pstmt = conexao.conn.prepareStatement(dropUserSql);
                conexao.pstmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o administrador ou usuário do banco.", e);
        } finally {
            conexao.desconectar();
        }
    }




    // Método para buscar um administrador na tabela Adm
    public Adm buscarAdm(String username){
        conexao.conectar();

        try {

            // Instrução SQL para excluir um administrador na tabela Adm
            String sql = "SELECT * FROM adm WHERE username = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, username);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            /// Obtem os dados do ResultSet
            if (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String login = conexao.rs.getString(2);
                String senha = conexao.rs.getString(3);

                // Cria um objeto Adm com os dados do ResultSet
                return new Adm(id,login, senha);
            }
            return null;
        }catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    // Método para buscar um administrador na tabela Adm
    public Adm buscarAdm(String username, String senha){
        conexao.conectar();

        try {

            // Instrução SQL para excluir um administrador na tabela Adm
            String sql = "SELECT * FROM adm WHERE username = ? AND senha = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, username);
            conexao.pstmt.setString(2, senha);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();

            /// Obtem os dados do ResultSet
            if (conexao.rs.next()) {
                UUID id = (UUID) conexao.rs.getObject(1);
                String login = conexao.rs.getString(2);
                String senhaAtualizada = conexao.rs.getString(3);

                // Cria um objeto Adm com os dados do ResultSet
                return new Adm(id,login, senhaAtualizada);
            }
            return null;
        }catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    // Método para listar todos os administradores na tabela Adm
    public List<Adm> listarAdms(){

        // Instrução SQL para listar todos os administradores na tabela Adm
        String sql = "SELECT * FROM adm";

        // Essa linha cria uma lista vazia chamada adms, pronta para armazenar objetos do tipo Adm.
        // Usamos o ArrayList para faciliar a manipulação da lista.
        List<Adm> adms = new ArrayList<>();

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
                String login = conexao.rs.getString(2);
                String senha = conexao.rs.getString(3);

                // Cria um objeto Adm com os dados do ResultSet
                Adm adm = new Adm(id, login, senha);

                // Adiciona o objeto Adm na lista de administradores
                adms.add(adm);
            }
        }catch (SQLException e) {

            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }

        // Retorna a lista de administradores
        return adms;
    }
}

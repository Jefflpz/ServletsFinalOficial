package org.example.crud_site.dao;

// Importando a classe Adm para usar os seus atributos e métodos.

import org.example.crud_site.model.Adm;

//importando a classe SQLException para tratar os erros de SQL.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando a classe ArrayList para criar uma lista de administradores
import java.util.ArrayList;
import java.util.List;

// Importando a classe UUID para usar nos ids.
import java.util.UUID;

//Classe AdmDAO
public class AdmDAO{

    // objeto que acessa o atributo conn
    private Conexao conexao;

    //Construtor que atribui a conexao uma nova instância com os atribuitos da classe Conexao.
    public AdmDAO() {
        conexao = new Conexao();
    }

    // Método para inserir um novo registro na tabala Adm
    public boolean inserirAdm(String username, String senha) {
        conexao.conectar();

        // Cria uma instrução SQL para inserir um novo administrador na tabela Adm.
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("INSERT INTO adm (username, senha) VALUES (?,?)")){


            // Define os valores dos parâmetros da consulta
            pstmt.setString(1, username);
            pstmt.setString(2, senha);

            // retorna true se a quantidade de registros afetados for maior que 0
            return pstmt.executeUpdate() > 0;

        }catch (SQLException e) {
            // Retorna false caso ocorra algum erro.
            return false;
        }finally {
            conexao.desconectar();
        }
    }

    // Método para alterar a senha de um administrador na tabela Adm
    public boolean alterarSenhaAdm(String novaSenha, UUID id) {
        conexao.conectar();

        // Cria uma instrução SQL para atualizar a senha do administrador na tabela Adm.
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE adm SET senha=? WHERE id=?")){


            // Define os valores dos parâmetros na consulta SQL
            pstmt.setString(1, novaSenha);
            pstmt.setObject(2, id);

            // Executa a instrução SQL e armazena o resultado na variável rows.
            int rows = pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro.
            if (rows > 0) {
                // Retorna true caso a senha tenha sido alterada.
                return true;
            }else {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
        }catch (SQLException e){
            // Retorna false caso ocorra algum erro.
            return false;
        }finally {
            conexao.desconectar();
        }
    }

    // Método para alterar o login de um administrador na tabela Adm
    public boolean alterarLoginAdm(String novoUsername, UUID id) {
        conexao.conectar();

        // Cria uma instrução SQL para atualizar o login do administrador na tabela Adm.
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("UPDATE adm SET username=? WHERE id = ?")) {


            // Define os valores dos parâmetros na consulta SQL
            pstmt.setString(1, novoUsername);
            pstmt.setObject(2, id);

            // Executa a instrução SQL.
            int rows = pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro.
            if (rows > 0) {
                // retorna true caso o login tenha sido alterado.
                return true;
            }else {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
        }catch (SQLException e){
            // Retorna false caso ocorra algum erro.
            return false;
        }finally {
            conexao.desconectar();
        }
    }


    // Método para excluir um administrador na tabela Adm
    public boolean excluirAdm(UUID id) {
        conexao.conectar();
        try {
            // Primeiro, obtemos o nome do usuário associado ao administrador que será excluído
            PreparedStatement pstmt = conexao.getConn().prepareStatement("DELETE FROM adm WHERE id = ?");
            pstmt.setObject(1, id);
            pstmt.execute();
            return true;

        } catch (SQLException e) {
            return false;
        } finally {
            conexao.desconectar();
        }
    }




    // Método para buscar um administrador na tabela Adm pelo nome
    public Adm buscarAdm(String username){
        conexao.conectar();

        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM adm WHERE username = ?");
        ) {

            pstmt.setString(1, username);

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            /// Obtem os dados do ResultSet
            if (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String login = rs.getString(2);
                String senha = rs.getString(3);

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

    // Método para buscar um administrador na tabela Adm pelo nome e senha
    public Adm buscarAdm(String username, String senha){

        conexao.conectar();

        // Cria uma instrução SQL para buscar um administrador na tabela Adm.
        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM adm WHERE username = ? AND senha = ?")){

            // Define os valores dos parâmetros na consulta SQL
            pstmt.setString(1, username);
            pstmt.setString(2, senha);

            // Armazena o resultado da consulta no objeto ResultSet
            ResultSet rs = pstmt.executeQuery();

            /// Obtem os dados do ResultSet
            if (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                String login = rs.getString(2);
                String senhaAtualizada = rs.getString(3);

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


        // Essa linha cria uma lista vazia chamada adms, pronta para armazenar objetos do tipo Adm.
        // Usamos o ArrayList para faciliar a manipulação da lista.
        List<Adm> adms = new ArrayList<>();
        conexao.conectar();


        try (PreparedStatement pstmt = conexao.getConn().prepareStatement("SELECT * FROM adm")){

            // Armazena o resultado da consulta no objeto ResultSet.
            ResultSet rs = pstmt.executeQuery();

            // Obtem os dados do ResultSet e armazena na lista de administradores.
            // Usamos o while para percorrer o ResultSet caso haja mais de um registro.
            while (rs.next()) {

                // Pega os dados do ResultSet
                UUID id = (UUID) rs.getObject(1);
                String login = rs.getString(2);
                String senha = rs.getString(3);

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

package org.example.crud_site.dao;

// Importando a classe Adm para usar os seus atributos e métodos.
import org.example.crud_site.model.Adm;

// Importando as bibliotecas necessárias para essa classe.
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AdmDAO {

    // Atributo estático para a única instância da classe
    private static AdmDAO instanciaUnica;

    // Atributo para acessar o banco de dados.
    private Conexao conexao;

    //O padrão Singleton é um padrão de design que garante que uma classe
    // tenha apenas uma única instância durante todo o ciclo de vida da aplicação
    //  e fornece um ponto global de acesso a essa instância. Esse padrão é útil
    //  em situações onde é necessário haver apenas um objeto compartilhado por
    //  todo o sistema, no nosso caso 'conexao'.


    // Construtor privado para impedir múltiplas instâncias.
    private AdmDAO() {
        conexao = new Conexao();
    }

    // Método público para obter a única instância da classe(Singleton)
    // Isso significa que se a classe já tiver sido instanciada, ela retornará a mesma instância.
    public static AdmDAO getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new AdmDAO();
        }
        return instanciaUnica;
    }


    // Método para inserir um novo registro na tabala Adm
    public boolean inserir(Adm adm) {

        conexao.conectar();
        try {

            // Instrução SQL para inserir um administrador na tabela Adm.
            String sql = "INSERT INTO adm (username, senha) VALUES (?,?)";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros da consulta
            conexao.pstmt.setString(1, adm.getUsername());
            conexao.pstmt.setString(2, adm.getSenha());

            //Executa a instrução SQL.
            return conexao.pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexao.desconectar();
        }
    }

//    // Método para alterar a senha de um administrador na tabela Adm
    public boolean atualizarSenha(String username, String senha) {
        conexao.conectar();
        try {

            // Instrução SQL para inserir um administrador na tabela Adm.
             String sql = "UPDATE adm SET senha=? WHERE username=?";
             conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL
            conexao.pstmt.setString(1, senha);
            conexao.pstmt.setObject(2, username);

            // Executa a instrução SQL.
            int rows = conexao.pstmt.executeUpdate();

            // Verifica se a instrução SQL alterou algum registro.
            if (rows == 0) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
            return rows > 0;
        }catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar o registro.", e);

        }finally {
            conexao.desconectar();
        }
    }


    // Método para excluir um administrador na tabela Adm
    public boolean excluirAdm(String username){

        // recebemos o usernamae do administrador que queremos excluir
        // , porém antes do método ser chamado, quem o chamou terá que confirmar
        //  a senha de quem ele quer excluir sendo possível o gerenciamento
        //  da equipe por um lider

        conexao.conectar();
        try{
            // Instrução SQL para excluir um administrador na tabela Adm
            String sql = "DELETE FROM adm WHERE username = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Define os valores dos parâmetros na consulta SQL
            conexao.pstmt.setString(1, username);

            // executa instrução SQL
            return conexao.pstmt.executeUpdate()> 0;
        }catch (SQLException e){
             throw new RuntimeException("Erro ao excluir o registro.", e);
        }finally {
            conexao.desconectar();
        }
    }


    // Método para buscar um administrador na tabela Adm
    public Adm buscarAdm(String username){

        // Cria um objeto Adm para armazenar os dados do administrador e ser o retorno método.
        Adm adm = null;

        conexao.conectar();

        try {

            // Instrução SQL para excluir um administrador na tabela Adm
            String sql = "SELECT * FROM adm WHERE username = ?";
            conexao.pstmt = conexao.conn.prepareStatement(sql);
            conexao.pstmt.setString(1, username);

            // Armazena o resultado da consulta no objeto ResultSet
            conexao.rs = conexao.pstmt.executeQuery();
            
            if (conexao.rs.next()) {
                /// Obtem os dados do ResultSet
                UUID idAdm = (UUID) conexao.rs.getObject(1);
                String login = conexao.rs.getString(2);
                String senha = conexao.rs.getString(3);

                // Cria um objeto Adm com os dados do ResultSet
                adm = new Adm(idAdm, login, senha);
            }
            // Executa a instrução SQL
            conexao.pstmt.execute();
        }catch (SQLException e) {
            // Retorna null caso ocorra algum erro.
            return null;
        }finally {
            conexao.desconectar();
        }
        // Retorna objeto conforme a consulta
        return adm;
    }

    // Método para listar todos os administradores na tabela Adm
    public List<Adm> listarAdms(){

        // Instrução SQL para listar todos os administradores na tabela Adm
        String sql = "SELECT * FROM adm";


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
                UUID id =(UUID) conexao.rs.getObject(1);
                String login = conexao.rs.getString(2);
                String senha = conexao.rs.getString(3);

                // Cria um objeto Adm com os dados do ResultSet
                Adm admCompleto = new Adm( id,login, senha);

                // Adiciona o objeto Adm na lista de administradores
                adms.add(admCompleto);
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

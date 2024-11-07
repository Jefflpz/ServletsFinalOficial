package org.example.crud_site.dao;

// importa todo o pacote java.sql
import java.sql.*;

// Classe Conexao
public class Conexao {

    // Atributo conection
    private Connection conn;

    //Método para retornar a conexão com o banco de dados
    public Connection getConn() {
        return conn;
    }
    //Método para estabelecer a conexão com o banco de dados
    public void conectar() {
        try {

            Class.forName("org.postgresql.Driver");

        // Pega as informações do arquivo .env e coloca em variáveis
            String url = System.getenv("DATABASE_URL");
            String user = System.getenv("DATABASE_USER");
            String password = System.getenv("DATABASE_PASSWORD");

        // Atribui a conn a Conexao que o "DriverManager.getConnection(url, user, password);" retorna
            this.conn = DriverManager.getConnection(url, user, password);

        }catch(ClassNotFoundException e) {
            // Se o driver não for encontrado, exibe a mensagem de erro
            e.printStackTrace();
        }catch (SQLException e) {
            // Se houver algum erro de conexão, exibe a mensagem de erro
            e.printStackTrace();
        }
    }

    // Fecha a conexão com o banco de dados
    public void desconectar() {
        try {
            // se a conexão não for nula, fecha a conexão
            if (conn != null) {
                conn.close();
            }
        }catch(SQLException e) {
            // Se houver algum erro ao fechar a conexão, exibe a mensagem de erro
            e.printStackTrace();
        }
    }
}
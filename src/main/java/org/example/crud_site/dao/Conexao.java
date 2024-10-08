package org.example.crud_site.dao;

// importa o arquvio.env
import io.github.cdimascio.dotenv.Dotenv;


import java.sql.*;

public class Conexao {
    public Connection conn;
    public PreparedStatement pstmt;
    public ResultSet rs;

    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");

        //  Lê o arquivo.env
            Dotenv dotenv = Dotenv.configure()
                    .directory("C:\\Users\\jeffersonlopes-ieg\\IdeaProjects\\CRUD_Site\\.env")  // Diretório onde o arquivo está
                    .filename(".env")  // Nome específico do arquivo
                    .load();

            String url = dotenv.get("DATABASE_URL");
            String user = dotenv.get("DATABASE_USER");
            String password = dotenv.get("DATABASE_PASSWORD");

        // Retorna a conexão com o banco de dados
            this.conn = DriverManager.getConnection(url, user, password);
            return conn;
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Fecha a conexão com o banco de dados
    public void desconectar() {
        try {
            if (conn != null) {
                conn.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}

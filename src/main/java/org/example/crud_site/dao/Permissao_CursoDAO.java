package org.example.crud_site.dao;

// Importando a classe Permissao_Curso para usar os seus atributos e métodos.

// Importando as classes necessárias para a conexão com o banco de dados e a execução de consultas SQL.


import org.example.crud_site.model.Permissao_Curso;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Permissao_CursoDAO {


    // Atributo estático para a única instância da classe
    private static Permissao_CursoDAO instanciaUnica;
    // Nomeando as bibliotecas necessárias para a conexão com o banco de
    // dados para facilitar a leitura do código.
    private Conexao conexao;

    //O padrão Singleton é um padrão de design que garante que uma classe
    // tenha apenas uma única instância durante todo o ciclo de vida da aplicação
    //  e fornece um ponto global de acesso a essa instância. Esse padrão é útil
    //  em situações onde é necessário haver apenas um objeto compartilhado por
    //  todo o sistema, no nosso caso 'conexao'.

    // Construtor privado para evitar multiplas instâncias da classe.
    private Permissao_CursoDAO(){
        conexao = new Conexao();
    }

    // Método público para obter a única instância da classe(Singleton)
    // Isso significa que se a classe já tiver sido instanciada, ela retornará a mesma instância.
    public static Permissao_CursoDAO getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Permissao_CursoDAO();
        }
        return instanciaUnica;
    }

    public boolean autorizarPermissao(UUID id_curso) {

        // No próprio try usamos esse comando: "Connection conexao = Conexao.conectar()".
        // Ele garante que ela será fechada automaticamente após o uso, mesmo que ocorra uma exceção.
        try{

            // Instrução SQL para alterar um administrador na tabela Adm.
            String sql = "UPDATE Permissao_Curso SET permissao = TRUE WHERE id_cuso =?";

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Acessa o 'SET' de Permissão_Curso para mudar a autorização do curso no banco de dados.
            conexao.pstmt.setObject(1, id_curso);

            // Retorna se o comando SQL foi executado com sucesso.
            return conexao.pstmt.executeUpdate() > 0;
        }catch(SQLException e) {
            // Retorna false se ocorrer algum erro na execução do comando SQL.
            return false;
        }
    }

    public List<Permissao_Curso> listarPermissoes(){

        // Instrução SQL para listar os administradores na tabela Adm.
        String sql = "SELECT * FROM permissao_curso";

        // Essa linha cria uma lista vazia chamada adms, pronta para armazenar objetos do tipo Adm.
        // Usamos o ArrayList para faciliar a manipulação da lista.
        List<Permissao_Curso> permissoes = new ArrayList<>();
        try{

            // Prepara a instrução SQL para ser executada.
            conexao.pstmt = conexao.conn.prepareStatement(sql);

            // Executa a instrução SQL e armazena os resultados em um ResultSet.
            conexao.rs = conexao.pstmt.executeQuery();

            // Enquanto houver registros no ResultSet, pega os dados de cada registro e cria um objeto Adm.
            while(conexao.rs.next()){

                //pega os dados de cada registro e cria um objeto Adm.
                //ignora o id_permissao, pois é gerado automaticamente pelo banco de dados.
                UUID id_conta = (UUID) conexao.rs.getObject(2);
                boolean permissao =  conexao.rs.getBoolean(3);
                String dt_solicitacao = conexao.rs.getString(4);
                String dt_autorizacao = conexao.rs.getString(5);
                UUID id_curso = (UUID) conexao.rs.getObject(6);
                UUID id_autorizador = (UUID) conexao.rs.getObject(7);

                Permissao_Curso permissaoCurso = new Permissao_Curso(id_conta, permissao, dt_solicitacao, dt_autorizacao, id_curso, id_autorizador);
                permissoes.add(permissaoCurso);
            }
            return permissoes;
        }catch(SQLException e){
            return null;
        }
    }
}

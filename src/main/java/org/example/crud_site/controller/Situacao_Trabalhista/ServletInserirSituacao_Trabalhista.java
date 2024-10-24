package org.example.crud_site.controller.Situacao_Trabalhista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Situacao_TrabalhistaDAO;
import org.example.crud_site.model.Situacao_Trabalhista;

import java.io.IOException;

@WebServlet("/inserirSituacaoTrabalhista")
public class ServletInserirSituacao_Trabalhista extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Obtém o parâmetro do nome da situação trabalhista da requisição
        String nomeSituacao = req.getParameter("nome");

        // Cria uma instância do DAO para realizar a inserção
        Situacao_TrabalhistaDAO situacaoTrabalhistaDAO = new Situacao_TrabalhistaDAO();
        Situacao_Trabalhista situacaoTrabalhista = new Situacao_Trabalhista(nomeSituacao);

        // Chama o método para inserir a nova situação trabalhista
        boolean resultado = situacaoTrabalhistaDAO.inserirSetor(situacaoTrabalhista);

        // Verifica se a inserção foi bem-sucedida
        if (resultado) {
            // Se a inserção foi bem-sucedida, redireciona para a página de sucesso
            req.setAttribute("mensagem", "Situação trabalhista inserida com sucesso.");
            req.getRequestDispatcher("sucesso.jsp").forward(req, res);
            return; // Para evitar a execução do código abaixo
        }

        // Se não for bem-sucedida, define uma mensagem de erro e encaminha para uma página de erro
        req.setAttribute("erro", "Falha ao inserir a situação trabalhista.");
        req.getRequestDispatcher("erro.jsp").forward(req, res);
    }
}

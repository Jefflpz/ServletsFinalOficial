package org.example.crud_site.controller.Situacao_Trabalhista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Situacao_TrabalhistaDAO;

import java.io.IOException;

@WebServlet("/excluirSituacaoTrabalhista")
public class ServletExcluirSituacao_Trabalhista extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome da situação trabalhista a partir da requisição
        String nomeSituacaoTrabalhista = req.getParameter("nome_situacao_trabalhista");

        // Cria uma instância do DAO para realizar a exclusão
        Situacao_TrabalhistaDAO situacaoTrabalhistaDAO = new Situacao_TrabalhistaDAO();

        // Tenta excluir a situação trabalhista
        try {
            situacaoTrabalhistaDAO.excluirSituacao_Trabalhista(nomeSituacaoTrabalhista);
        } catch (RuntimeException e) {
            // Se ocorrer um erro, redireciona para erro.jsp
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
            return; // Para garantir que a execução não continue
        }

        // Redireciona para a página de sucesso após a exclusão
        req.setAttribute("mensagem", "Situação trabalhista excluída com sucesso.");
        req.getRequestDispatcher("sucesso.jsp").forward(req, res);
    }
}

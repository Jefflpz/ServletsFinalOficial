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
        if (situacaoTrabalhistaDAO.excluirSituacao_Trabalhista(nomeSituacaoTrabalhista)) {
            req.getRequestDispatcher("listarSituacoes_Trabalhistas").forward(req, res);
        }
    }
}

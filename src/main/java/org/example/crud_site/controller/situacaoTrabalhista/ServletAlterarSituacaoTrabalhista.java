package org.example.crud_site.controller.situacaoTrabalhista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.SituacaoTrabalhistaDAO;

import java.io.IOException;

@WebServlet("/alterarSituacaoTrabalhista")
public class ServletAlterarSituacaoTrabalhista extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String nomeAtual = req.getParameter("nome_atual");
        String novoNome = req.getParameter("novo_nome");

        SituacaoTrabalhistaDAO situacaoTrabalhistaDAO = new SituacaoTrabalhistaDAO();
        boolean resultado = situacaoTrabalhistaDAO.alterarNomeSituacaoTrabalhista(nomeAtual, novoNome);

        if (resultado) {
            req.setAttribute("mensagem", "Situação trabalhista alterada com sucesso.");
            req.getRequestDispatcher("pages/situacaoTrabalhista.jsp").forward(req, res);
            return;
        }

        req.setAttribute("erro", "Falha ao alterar a situação trabalhista.");
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

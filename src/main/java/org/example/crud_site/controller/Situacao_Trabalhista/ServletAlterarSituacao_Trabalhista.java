package org.example.crud_site.controller.Situacao_Trabalhista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Situacao_TrabalhistaDAO;

import java.io.IOException;

@WebServlet("/alterarSituacaoTrabalhista")
public class ServletAlterarSituacao_Trabalhista extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String nomeAtual = req.getParameter("nome_atual");
        String novoNome = req.getParameter("novo_nome");

        Situacao_TrabalhistaDAO situacaoTrabalhistaDAO = new Situacao_TrabalhistaDAO();
        boolean resultado = situacaoTrabalhistaDAO.alterarNome(nomeAtual, novoNome);

        if (resultado) {
            req.setAttribute("mensagem", "Situação trabalhista alterada com sucesso.");
            req.getRequestDispatcher("sucesso.jsp").forward(req, res);
            return;
        }

        req.setAttribute("erro", "Falha ao alterar a situação trabalhista.");
        req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
    }
}

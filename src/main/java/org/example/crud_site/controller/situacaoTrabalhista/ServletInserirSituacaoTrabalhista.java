package org.example.crud_site.controller.situacaoTrabalhista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.SituacaoTrabalhistaDAO;
import org.example.crud_site.model.SituacaoTrabalhista;

import java.io.IOException;

@WebServlet("/inserirSituacaoTrabalhista")
public class ServletInserirSituacaoTrabalhista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        String nomeSituacao = request.getParameter("cadastrar");

        // Instancia o DAO para interagir com o banco de dados.
        SituacaoTrabalhistaDAO situacaoTrabalhistaDAO = new SituacaoTrabalhistaDAO();
        SituacaoTrabalhista situacaoTrabalhistaNova = new SituacaoTrabalhista(nomeSituacao);
        try {
            if (situacaoTrabalhistaDAO.inserirSituacaoTrabalhista(situacaoTrabalhistaNova.getNome())) {
                response.sendRedirect(request.getContextPath() + "/listarSituacao_Trabalhistas");
            } else {
                request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);

        }
    }
}

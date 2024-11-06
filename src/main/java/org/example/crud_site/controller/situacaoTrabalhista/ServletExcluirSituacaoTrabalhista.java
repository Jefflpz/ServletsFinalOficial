package org.example.crud_site.controller.situacaoTrabalhista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.SituacaoTrabalhistaDAO;

import java.io.IOException;

@WebServlet("/excluirSituacaoTrabalhista")
public class ServletExcluirSituacaoTrabalhista extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros da requisição
        String nomeSituacao = request.getParameter("nome");

        // Cria uma instância do DAO para realizar a exclusão
        SituacaoTrabalhistaDAO situacaoTrabalhistaDAO = new SituacaoTrabalhistaDAO();

        if (situacaoTrabalhistaDAO.excluirSituacaoTrabalhista(nomeSituacao)) {
            request.getRequestDispatcher("listarSituacaoTrabalhista").forward(request, response);
        }
        else{
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

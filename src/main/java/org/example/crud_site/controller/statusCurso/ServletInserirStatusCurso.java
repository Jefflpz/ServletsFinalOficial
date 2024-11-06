package org.example.crud_site.controller.statusCurso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.StatusCursoDAO;
import org.example.crud_site.model.StatusCurso;

import java.io.IOException;

@WebServlet("/inserirStatusCurso")
public class ServletInserirStatusCurso extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String nomeStatusCurso = request.getParameter("cadastrar");

        // Instancia o DAO para interagir com o banco de dados.
        StatusCursoDAO statusCursoDAO = new StatusCursoDAO();
        StatusCurso statusNovo = new StatusCurso(nomeStatusCurso);
        try {
            if (statusCursoDAO.inserirStatusCurso(statusNovo.getNome())) {
                response.sendRedirect(request.getContextPath() + "/listarStatusCurso");
            } else {
                request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

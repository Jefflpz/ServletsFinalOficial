package org.example.crud_site.controller.statusCurso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.StatusCursoDAO;
import org.example.crud_site.model.Status_Curso;

import java.io.IOException;

@WebServlet("/inserirStatus_Curso")
public class ServletInserirStatusCurso extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String nomeStatusCurso = request.getParameter("cadastrar");

        // Instancia o DAO para interagir com o banco de dados.
        StatusCursoDAO statusCursoDAO = new StatusCursoDAO();
        Status_Curso statusNovo = new Status_Curso(nomeStatusCurso);
        try {
            if (statusCursoDAO.inserirStatusCurso(statusNovo.getNome())) {
                response.sendRedirect(request.getContextPath() + "/listarStatus_Curso");
            } else {
                request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

package org.example.crud_site.controller.statusCurso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.StatusCursoDAO;

import java.io.IOException;

@WebServlet("/excluirStatusCurso")
public class ServletExcluirStatusCurso extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros da requisição
        String nomeStatus = request.getParameter("nome");

        // Cria uma instância do DAO para realizar a exclusão
        StatusCursoDAO statusCursoDAO = new StatusCursoDAO();

        if (statusCursoDAO.excluirStautsCurso(nomeStatus)) {
            request.getRequestDispatcher("listarStatus_Curso").forward(request, response);
        }
        else{
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

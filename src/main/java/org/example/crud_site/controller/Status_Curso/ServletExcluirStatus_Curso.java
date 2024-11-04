package org.example.crud_site.controller.Status_Curso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Status_CursoDAO;

import java.io.IOException;

@WebServlet("/excluirStatusCurso")
public class ServletExcluirStatus_Curso extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do status de curso a partir da requisição
        String nomeStatus_Curso = req.getParameter("nome_status_curso");

        // Cria uma instância do DAO para realizar a exclusão
        Status_CursoDAO status_CursoDAO = new Status_CursoDAO();

        // Tenta excluir o status de curso
        if (status_CursoDAO.excluirStauts_Curso(nomeStatus_Curso)){
            req.getRequestDispatcher("listarStatus_Curso").forward(req, res);
        }
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);

    }
}

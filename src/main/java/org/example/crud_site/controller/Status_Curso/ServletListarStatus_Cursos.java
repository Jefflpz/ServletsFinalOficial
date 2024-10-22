package org.example.crud_site.controller.Status_Curso;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Status_CursoDAO;
import org.example.crud_site.model.Status_Curso;

@WebServlet("/listarStatus_Curso")
public class ServletListarStatus_Cursos {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Status_CursoDAO status_cursoDAO = new Status_CursoDAO();
        List<Status_Curso> status_Cursos = status_cursoDAO.listarStatus_Curso();
        req.setAttribute("status_cursoList", status_Cursos);
        req.getRequestDispatcher("status_curso.jsp").forward(req, res);
    }
}

package org.example.crud_site.controller.Permissao_Curso;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.model.Permissao_Curso;
import org.example.crud_site.dao.Permissao_CursoDAO;

@WebServlet("/listarPermissao_Curso")
public class ServletListarPermissoes_Cursos {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Permissao_CursoDAO permissao_cursoDAO = new Permissao_CursoDAO();
        List<Permissao_Curso> listaPermissao_Curso = permissao_cursoDAO.listarPermissoes_Curso();
        req.setAttribute("listaPermissao_Curso", listaPermissao_Curso);
        req.getRequestDispatcher("permissao_curso.jsp").forward(req, res);

    }
}

package org.example.crud_site.controller.statusCurso;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.StatusCursoDAO;
import org.example.crud_site.model.StatusCurso;

// Define a servlet que responde ao caminho /listarStatus_Curso
@WebServlet("/listarStatusCurso")
public class ServletListarStatusCursos extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de status de curso
        StatusCursoDAO status_cursoDAO = new StatusCursoDAO();

        // Obtém a lista de status de cursos através do DAO
        List<StatusCurso> status_Cursos = status_cursoDAO.listarStatusCurso();

        // Define a lista de status de cursos como um atributo da requisição
        req.setAttribute("listarStatus_curso", status_Cursos);

        // Encaminha a requisição e a resposta para a página status_curso.jsp
        req.getRequestDispatcher("pages/statusCurso.jsp").forward(req, res);
    }
}

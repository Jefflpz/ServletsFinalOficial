package org.example.crud_site.controller.Status_Curso;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Status_CursoDAO;
import org.example.crud_site.model.Status_Curso;

// Define a servlet que responde ao caminho /listarStatus_Curso
@WebServlet("/listarStatus_Curso")
public class ServletListarStatus_Cursos extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de status de curso
        Status_CursoDAO status_cursoDAO = new Status_CursoDAO();

        // Obtém a lista de status de cursos através do DAO
        List<Status_Curso> status_Cursos = status_cursoDAO.listarStatus_Curso();

        // Define a lista de status de cursos como um atributo da requisição
        req.setAttribute("status_cursoList", status_Cursos);

        // Encaminha a requisição e a resposta para a página status_curso.jsp
        req.getRequestDispatcher("/pages/statusCurso.jsp").forward(req, res);
    }
}

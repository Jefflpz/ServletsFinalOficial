package org.example.crud_site.controller.Permissao_Curso;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.model.Permissao_Curso;
import org.example.crud_site.dao.Permissao_CursoDAO;

// Define a servlet que responde ao caminho /listarPermissao_Curso
@WebServlet("/listarPermissao_Curso")
public class ServletListarPermissoes_Cursos {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de permissões de curso
        Permissao_CursoDAO permissao_cursoDAO = new Permissao_CursoDAO();

        // Obtém a lista de permissões de curso através do DAO
        List<Permissao_Curso> listaPermissao_Curso = permissao_cursoDAO.listarPermissoes_Curso();

        // Define a lista de permissões de curso como um atributo da requisição
        req.setAttribute("listaPermissao_Curso", listaPermissao_Curso);

        // Encaminha a requisição e a resposta para a página permissao_curso.jsp
        req.getRequestDispatcher("permissao_curso.jsp").forward(req, res);
    }
}

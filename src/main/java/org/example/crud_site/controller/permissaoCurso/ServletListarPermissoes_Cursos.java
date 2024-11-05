package org.example.crud_site.controller.permissaoCurso;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.model.PermissaoCurso;
import org.example.crud_site.dao.PermissaoCursoDAO;

// Define a servlet que responde ao caminho /listarPermissao_Curso
@WebServlet("/listarPermissao_Curso")
public class ServletListarPermissoes_Cursos extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de permissões de curso
        PermissaoCursoDAO permissao_cursoDAO = new PermissaoCursoDAO();

        // Obtém a lista de permissões de curso através do DAO
        List<PermissaoCurso> listaPermissao_Curso = permissao_cursoDAO.listarPermissoesCurso();

        // Define a lista de permissões de curso como um atributo da requisição
        req.setAttribute("listaPermissao_Curso", listaPermissao_Curso);

        // Encaminha a requisição e a resposta para a página permissao_curso.jsp
        req.getRequestDispatcher("pages/permissaoCurso.jsp").forward(req, res);

    }
}

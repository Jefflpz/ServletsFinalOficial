package org.example.crud_site.controller.Status_Curso;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Status_CursoDAO;
import org.example.crud_site.model.Status_Curso;

import java.io.IOException;

@WebServlet("/buscarStatus_Curso")
public class ServletBuscarStatus_Curso extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Obtém o parâmetro "nomeStatus_Curso" da requisição
        String nomeStatus_Curso = req.getParameter("nomeStatus_Curso");

        // Cria uma instância do DAO para realizar a busca
        Status_CursoDAO status_CursoDAO = new Status_CursoDAO();
        Status_Curso status_Curso = status_CursoDAO.buscarStatus_Curso(nomeStatus_Curso);

        // Verifica se o status do curso foi encontrado
        if (status_Curso == null) {
            // Se não encontrado, define uma mensagem de erro e encaminha para uma página de erro
            req.setAttribute("erro", "Status do curso não encontrado.");
            req.getRequestDispatcher("erro.jsp").forward(req, res);
            return;
        }

        // Se encontrado, adiciona o status do curso como atributo na requisição
        req.setAttribute("status_Curso", status_Curso);
        // Encaminha para a página JSP que exibirá os dados do status do curso
        req.getRequestDispatcher("exibirStatus_Curso.jsp").forward(req, res);
    }
}

package org.example.crud_site.controller.Status_Curso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Status_CursoDAO;
import org.example.crud_site.model.Status_Curso;

import java.io.IOException;

@WebServlet("/inserirStatus_Curso")
public class ServletInserirStatus_Curso {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do status de curso a partir da requisição
        String nomeStatus_Curso = req.getParameter("nome_status_curso");

        // Cria uma instância do modelo e do DAO para inserir o status de curso
        Status_Curso status_Curso = new Status_Curso(nomeStatus_Curso);
        Status_CursoDAO status_CursoDAO = new Status_CursoDAO();

        // Insere o status de curso e verifica se foi bem-sucedido
        boolean inserido = status_CursoDAO.inserirStatus_curso(status_Curso);
        if (inserido) {
            // Redireciona para a página de sucesso
            req.setAttribute("mensagem", "Status de curso inserido com sucesso.");
            req.getRequestDispatcher("sucesso.jsp").forward(req, res);
            return;
        }

        // Caso contrário, redireciona para a página de erro
        req.setAttribute("erro", "Falha ao inserir o status de curso.");
        req.getRequestDispatcher("erro.jsp").forward(req, res);
    }
}

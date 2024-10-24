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
        try {
            status_CursoDAO.excluirStauts_Curso(nomeStatus_Curso);
        } catch (RuntimeException e) {
            // Se ocorrer um erro, redireciona para erro.jsp
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("erro.jsp").forward(req, res);
            return; // Para garantir que a execução não continue
        }

        // Redireciona para a página de sucesso após a exclusão
        req.setAttribute("mensagem", "Status de curso excluído com sucesso.");
        req.getRequestDispatcher("sucesso.jsp").forward(req, res);
    }
}

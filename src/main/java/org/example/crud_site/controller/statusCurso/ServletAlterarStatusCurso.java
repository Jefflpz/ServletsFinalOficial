package org.example.crud_site.controller.statusCurso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.StatusCursoDAO;
import org.example.crud_site.model.Status_Curso;

import java.io.IOException;

@WebServlet("/alterarStatus_Curso")
public class ServletAlterarStatusCurso extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome atual e o novo nome do status de curso a partir da requisição
        String nomeAtual = req.getParameter("nome_atual");
        String nomeNovo = req.getParameter("nome_novo");

        // Cria uma instância do Status_Curso e define o nome atual
        Status_Curso status_Curso = new Status_Curso(nomeAtual);

        // Cria uma instância do DAO para realizar a alteração
        StatusCursoDAO status_CursoDAO = new StatusCursoDAO();

        // Tenta alterar o nome do status de curso
        try {
            if (status_CursoDAO.alterarNomeStatusCurso(status_Curso, nomeNovo)) {
                req.setAttribute("mensagem", "Nome do status de curso alterado com sucesso.");
                req.getRequestDispatcher("pages/statusCurso.jsp").forward(req, res);
                return; // Para evitar a execução do código abaixo
            }
        } catch (RuntimeException e) {
            // Se ocorrer um erro, redireciona para erro.jsp com a mensagem de erro
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
            return;
        }

        // Caso a alteração não tenha sido bem-sucedida, redireciona para a página de erro
        req.setAttribute("erro", "Falha ao alterar o nome do status de curso.");
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

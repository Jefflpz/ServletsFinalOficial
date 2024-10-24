package org.example.crud_site.controller.Adm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;

import java.io.IOException;

@WebServlet("/excluirAdm")
public class ServletExcluirAdm extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém os parâmetros da requisição
        String username = req.getParameter("username");
        String senha = req.getParameter("senha");

        // Cria uma instância do DAO para realizar a exclusão
        AdmDAO admDAO = new AdmDAO();

        // Tenta excluir o administrador
        try {
            admDAO.excluirAdm(username, senha);
        } catch (RuntimeException e) {
            // Se ocorrer um erro, redireciona para erro.jsp
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
            return; // Para garantir que a execução não continue
        }

        // Redireciona para a página de sucesso após a exclusão
        req.setAttribute("mensagem", "Administrador excluído com sucesso.");
        req.getRequestDispatcher("sucesso.jsp").forward(req, res);
    }
}

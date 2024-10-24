package org.example.crud_site.controller.Adm;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;

import java.io.IOException;

@WebServlet("/alterarLoginAdm")
public class ServletAlterarLoginAdm extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Obtém os parâmetros da requisição
        String username = req.getParameter("username");
        String senha = req.getParameter("senha");
        String novoUsername = req.getParameter("novoUsername");

        // Cria uma instância do DAO para realizar a alteração do login
        AdmDAO admDAO = new AdmDAO();
        boolean resultado = admDAO.alterarLoginAdm(novoUsername, username, senha);

        // Verifica se a alteração do login foi bem-sucedida
        if (resultado) {
            // Se a alteração foi bem-sucedida, redireciona para a página de sucesso
            req.setAttribute("mensagem", "Login alterado com sucesso.");
            req.getRequestDispatcher("sucesso.jsp").forward(req, res);
            return; // Para evitar a execução do código abaixo
        }

        // Se não for bem-sucedida, define uma mensagem de erro e encaminha para uma página de erro
        req.setAttribute("erro", "Falha ao alterar o login.");
        req.getRequestDispatcher("erro.jsp").forward(req, res);
    }
}

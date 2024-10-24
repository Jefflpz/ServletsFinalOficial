package org.example.crud_site.controller.Adm;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;

import java.io.IOException;

@WebServlet("/alterarSenhaAdm")
public class ServletAlterarSenhaAdm {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Obtém os parâmetros da requisição
        String username = req.getParameter("username");
        String senhaAntiga = req.getParameter("senhaAntiga");
        String novaSenha = req.getParameter("novaSenha");

        // Cria uma instância do DAO para realizar a alteração da senha
        AdmDAO admDAO = new AdmDAO();
        boolean resultado = admDAO.alterarSenhaAdm(novaSenha, senhaAntiga, username);

        // Verifica se a alteração da senha foi bem-sucedida
        if (resultado) {
            // Se a alteração foi bem-sucedida, redireciona para a página de sucesso
            req.setAttribute("mensagem", "Senha alterada com sucesso.");
            req.getRequestDispatcher("sucesso.jsp").forward(req, res);
            return; // Para evitar a execução do código abaixo
        }

        // Se não for bem-sucedida, define uma mensagem de erro e encaminha para uma página de erro
        req.setAttribute("erro", "Falha ao alterar a senha.");
        req.getRequestDispatcher("erro.jsp").forward(req, res);
    }
}

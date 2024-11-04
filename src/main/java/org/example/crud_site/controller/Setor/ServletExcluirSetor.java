package org.example.crud_site.controller.Setor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.SetorDAO;

import java.io.IOException;

@WebServlet("/excluirSetor")
public class ServletExcluirSetor extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do setor a partir da requisição
        String nomeSetor = req.getParameter("nome_setor");
        // Cria uma instância do DAO para realizar a exclusão
        SetorDAO setorDAO = new SetorDAO();
        if (setorDAO.excluirSetor(nomeSetor)){
            req.getRequestDispatcher("listarSetor").forward(req, res);
        }
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

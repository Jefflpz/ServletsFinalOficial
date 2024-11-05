package org.example.crud_site.controller.setor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.SetorDAO;

import java.io.IOException;

@WebServlet("/excluirSetor")
public class ServletExcluirSetor extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros da requisição
        String nomeSetor = request.getParameter("nome");

        // Cria uma instância do DAO para realizar a exclusão
        SetorDAO setorDAO = new SetorDAO();

        if (setorDAO.excluirSetor(nomeSetor)) {
            request.getRequestDispatcher("listarSetor").forward(request, response);
        }else{
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

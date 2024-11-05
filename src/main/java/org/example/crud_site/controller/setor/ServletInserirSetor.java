package org.example.crud_site.controller.setor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.model.Setor;

@WebServlet("/inserirSetor")
public class ServletInserirSetor extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String nomeSetor = request.getParameter("cadastrar");

        // Instancia o DAO para interagir com o banco de dados.
        SetorDAO setorDAO = new SetorDAO();
        Setor setorNovo = new Setor(nomeSetor);
        try {
            if (setorDAO.inserir(setorNovo.getNome())) {
                response.sendRedirect(request.getContextPath() + "/listarSetor");
            } else {
                request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);

        }

    }
}

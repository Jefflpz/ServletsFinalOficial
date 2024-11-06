package org.example.crud_site.controller.adm;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;

import java.io.IOException;
import java.util.UUID;

@WebServlet(value = "/excluirADM")
public class ServletExcluirAdm extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros da requisição
        String id = request.getParameter("id");
        UUID uuid;

        try {
            // Converte o ID para UUID
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            // ID inválido, redireciona para uma página de erro
            request.setAttribute("errorMessage", "ID inválido");
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
            return;
        }

        // Cria uma instância do DAO para realizar a exclusão
        AdmDAO admDAO = new AdmDAO();
        System.out.println(uuid.toString());

        boolean excluido = admDAO.excluirAdm(uuid);

        // Verifica o resultado da exclusão e redireciona conforme o caso
        if (excluido) {
            response.sendRedirect("listarAdm");
        } else {
            request.setAttribute("errorMessage", "Falha ao excluir o administrador.");
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }

}

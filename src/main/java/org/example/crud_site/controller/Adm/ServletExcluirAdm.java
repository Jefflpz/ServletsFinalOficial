package org.example.crud_site.controller.Adm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/excluirAdm")
public class ServletExcluirAdm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros da requisição
        String id = request.getParameter("id");
        UUID uuid = UUID.fromString(id);
        // Cria uma instância do DAO para realizar a exclusão
        AdmDAO admDAO = new AdmDAO();

        if (admDAO.excluirAdm(uuid)) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().println("{\"success\": true");
//            response.sendRedirect("listarAdm");
            request.getRequestDispatcher("listarAdm").forward(request, response);
        }
        request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);

    }
}

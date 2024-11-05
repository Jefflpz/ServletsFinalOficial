package org.example.crud_site.controller.tipoVaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.TipoVagaDAO;
import org.example.crud_site.model.Tipo_Vaga;

import java.io.IOException;

@WebServlet("/inserirTipo_Vaga")
public class ServletInserirTipoVaga extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String nomeTipoVaga = request.getParameter("cadastrar");

        // Instancia o DAO para interagir com o banco de dados.
        TipoVagaDAO tipoVagaDAO = new TipoVagaDAO();
        Tipo_Vaga tipoVagaNovo = new Tipo_Vaga(nomeTipoVaga);
        try {
            if (tipoVagaDAO.inserirTipo_Vaga(tipoVagaNovo.getNome())) {
                response.sendRedirect(request.getContextPath() + "/listarTipo_Vaga");
            } else {
                request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

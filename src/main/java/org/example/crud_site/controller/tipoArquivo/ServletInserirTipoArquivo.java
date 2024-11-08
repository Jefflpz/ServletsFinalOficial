package org.example.crud_site.controller.tipoArquivo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.TipoArquivoDAO;
import org.example.crud_site.model.TipoArquivo;

import java.io.IOException;

@WebServlet("/inserirTipoArquivo")
public class ServletInserirTipoArquivo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String nomeTipoArquivo = request.getParameter("cadastrar");

        // Instancia o DAO para interagir com o banco de dados.
        TipoArquivoDAO tipoArquivoDAO = new TipoArquivoDAO();
        TipoArquivo tipoArquivoNovo = new TipoArquivo(nomeTipoArquivo);
        try {
            if (tipoArquivoDAO.inserirTipoArquivo(tipoArquivoNovo.getNome())) {
                response.sendRedirect(request.getContextPath() + "/listarTipoArquivo");
            } else {
                request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

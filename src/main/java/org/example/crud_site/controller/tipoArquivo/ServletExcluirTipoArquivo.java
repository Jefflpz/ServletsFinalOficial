package org.example.crud_site.controller.tipoArquivo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.dao.TipoArquivoDAO;

import java.io.IOException;

@WebServlet("/excluirTipoArquivo")
public class ServletExcluirTipoArquivo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o ID do setor da requisição
        String nomeTipoArquivo = request.getParameter("nome");

        // Cria uma instância do DAO para realizar a exclusão
        TipoArquivoDAO tipoArquivoDAO = new TipoArquivoDAO();

        // Tenta excluir o setor e redireciona conforme o resultado
        if (tipoArquivoDAO.excluirTipoArquivo(nomeTipoArquivo)) {
            request.getRequestDispatcher("listarTipoarquivo").forward(request, response);
        } else {
            request.getRequestDispatcher("pages/errorPage.jsp").forward(request, response);
        }
    }
}

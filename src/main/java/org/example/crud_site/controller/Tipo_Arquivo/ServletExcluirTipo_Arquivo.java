package org.example.crud_site.controller.Tipo_Arquivo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Tipo_ArquivoDAO;

import java.io.IOException;

@WebServlet("/excluirTipoArquivo")
public class ServletExcluirTipo_Arquivo extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do tipo de arquivo a partir da requisição
        String nomeTipo_Arquivo = req.getParameter("nome_tipo_arquivo");

        // Cria uma instância do DAO para realizar a exclusão
        Tipo_ArquivoDAO tipo_ArquivoDAO = new Tipo_ArquivoDAO();

        // Tenta excluir o tipo de arquivo
        if (tipo_ArquivoDAO.excluirTipo_Arquivo(nomeTipo_Arquivo)){
            req.getRequestDispatcher("listarTipo_Arquivo").forward(req, res);
        }
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

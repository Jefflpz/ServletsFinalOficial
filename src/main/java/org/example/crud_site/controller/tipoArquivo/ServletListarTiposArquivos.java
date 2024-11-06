package org.example.crud_site.controller.tipoArquivo;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.TipoArquivoDAO;
import org.example.crud_site.model.TipoArquivo;

// Define a servlet que responde ao caminho /listarTipo_arquivo
@WebServlet("/listarTipoarquivo")
public class ServletListarTiposArquivos extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de tipo de arquivo
        TipoArquivoDAO tipo_ArquivoDAO = new TipoArquivoDAO();

        // Obtém a lista de tipos de arquivos através do DAO
        List<TipoArquivo> tipos_Arquivos = tipo_ArquivoDAO.listarTipoArquivo();

        // Define a lista de tipos de arquivos como um atributo da requisição
        req.setAttribute("listarTipo_arquivo", tipos_Arquivos);

        // Encaminha a requisição e a resposta para a página listarTipo_arquivo.jsp
        req.getRequestDispatcher("pages/tipoArquivo.jsp").forward(req, res);
    }
}

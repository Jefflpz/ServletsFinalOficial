package org.example.crud_site.controller.Tipo_Arquivo;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Tipo_ArquivoDAO;
import org.example.crud_site.model.Tipo_Arquivo;

// Define a servlet que responde ao caminho /listarTipo_arquivo
@WebServlet("/listarTipo_arquivo")
public class ServletListarTipos_Arquivos extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de tipo de arquivo
        Tipo_ArquivoDAO tipo_ArquivoDAO = new Tipo_ArquivoDAO();

        // Obtém a lista de tipos de arquivos através do DAO
        List<Tipo_Arquivo> tipos_Arquivos = tipo_ArquivoDAO.listarTipo_Arquivo();

        // Define a lista de tipos de arquivos como um atributo da requisição
        req.setAttribute("listarTipo_Arquivo", tipos_Arquivos);

        // Encaminha a requisição e a resposta para a página listarTipo_arquivo.jsp
        req.getRequestDispatcher("pages/tipoArquivo.jsp").forward(req, res);
    }
}

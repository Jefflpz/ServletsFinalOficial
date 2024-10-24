package org.example.crud_site.controller.Tipo_Arquivo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Tipo_ArquivoDAO;
import org.example.crud_site.model.Tipo_Arquivo;

import java.io.IOException;

@WebServlet("/buscarTipo_Arquivo")
public class ServletBuscarTipo_Arquivo extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Obtém o parâmetro "nomeTipo_Arquivo" da requisição
        String nomeTipo_Arquivo = req.getParameter("nomeTipo_Arquivo");

        // Cria uma instância do DAO para realizar a busca
        Tipo_ArquivoDAO tipo_ArquivoDAO = new Tipo_ArquivoDAO();
        Tipo_Arquivo tipo_Arquivo = tipo_ArquivoDAO.buscarTipo_Arquivo(nomeTipo_Arquivo);

        // Verifica se o tipo de arquivo foi encontrado
        if (tipo_Arquivo == null) {
            // Se não encontrado, define uma mensagem de erro e encaminha para uma página de erro
            req.setAttribute("erro", "Tipo de arquivo não encontrado.");
            req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
            return;
        }

        // Se encontrado, adiciona o tipo de arquivo como atributo na requisição
        req.setAttribute("tipo_Arquivo", tipo_Arquivo);
        // Encaminha para a página JSP que exibirá os dados do tipo de arquivo
        req.getRequestDispatcher("exibirTipo_Arquivo.jsp").forward(req, res);
    }
}

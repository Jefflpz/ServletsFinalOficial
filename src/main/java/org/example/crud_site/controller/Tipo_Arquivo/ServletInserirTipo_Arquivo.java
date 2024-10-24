package org.example.crud_site.controller.Tipo_Arquivo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Tipo_ArquivoDAO;
import org.example.crud_site.model.Tipo_Arquivo;

import java.io.IOException;

@WebServlet("/inserirTipo_Arquivo")
public class ServletInserirTipo_Arquivo extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do tipo de arquivo a partir da requisição
        String nomeTipo_Arquivo = req.getParameter("nome_tipo_arquivo");

        // Cria uma instância do modelo e do DAO para inserir o tipo de arquivo
        Tipo_Arquivo tipo_Arquivo = new Tipo_Arquivo(nomeTipo_Arquivo);
        Tipo_ArquivoDAO tipo_ArquivoDAO = new Tipo_ArquivoDAO();

        // Insere o tipo de arquivo e verifica se foi bem-sucedido
        boolean inserido = tipo_ArquivoDAO.inserirTipo_Arquivo(tipo_Arquivo);
        if (inserido) {
            // Redireciona para a página de sucesso
            req.setAttribute("mensagem", "Tipo de arquivo inserido com sucesso.");
            req.getRequestDispatcher("sucesso.jsp").forward(req, res);
            return;
        }

        // Caso contrário, redireciona para a página de erro
        req.setAttribute("erro", "Falha ao inserir o tipo de arquivo.");
        req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
    }
}

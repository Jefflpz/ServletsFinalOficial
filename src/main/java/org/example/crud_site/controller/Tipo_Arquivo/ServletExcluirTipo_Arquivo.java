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
        try {
            tipo_ArquivoDAO.excluirTipo_Arquivo(nomeTipo_Arquivo);
        } catch (RuntimeException e) {
            // Se ocorrer um erro, redireciona para erro.jsp
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("erro.jsp").forward(req, res);
            return; // Para garantir que a execução não continue
        }

        // Redireciona para a página de sucesso após a exclusão
        req.setAttribute("mensagem", "Tipo de arquivo excluído com sucesso.");
        req.getRequestDispatcher("sucesso.jsp").forward(req, res);
    }
}

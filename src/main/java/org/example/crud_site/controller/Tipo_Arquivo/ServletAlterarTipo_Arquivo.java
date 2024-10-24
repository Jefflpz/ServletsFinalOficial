package org.example.crud_site.controller.Tipo_Arquivo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Tipo_ArquivoDAO;
import org.example.crud_site.model.Tipo_Arquivo;

import java.io.IOException;

@WebServlet("/alterarTipo_Arquivo")
public class ServletAlterarTipo_Arquivo extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém os parâmetros da requisição
        String nomeAtual = req.getParameter("nome_atual");
        String novoNome = req.getParameter("novo_nome");

        // Cria uma instância do modelo Tipo_Arquivo e define o nome atual
        Tipo_Arquivo tipo_arquivo = new Tipo_Arquivo(nomeAtual);

        // Cria uma instância do DAO para realizar a alteração
        Tipo_ArquivoDAO tipo_arquivoDAO = new Tipo_ArquivoDAO();

        // Tenta alterar o nome do tipo de arquivo
        try {
            tipo_arquivoDAO.alterarNome(tipo_arquivo, novoNome);
        } catch (RuntimeException e) {
            // Se ocorrer um erro, redireciona para erro.jsp
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("erro.jsp").forward(req, res);
            return; // Para garantir que a execução não continue
        }

        // Redireciona para a página de sucesso após a alteração
        req.setAttribute("mensagem", "Tipo de arquivo alterado com sucesso.");
        req.getRequestDispatcher("sucesso.jsp").forward(req, res);
    }
}

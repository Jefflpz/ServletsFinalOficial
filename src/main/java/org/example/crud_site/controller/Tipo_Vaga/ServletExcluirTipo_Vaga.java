package org.example.crud_site.controller.Tipo_Vaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Tipo_VagaDAO;

import java.io.IOException;

@WebServlet("/excluirTipo_Vaga")
public class ServletExcluirTipo_Vaga extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do tipo de vaga a partir da requisição
        String nomeTipo_Vaga = req.getParameter("nome_tipo_vaga");

        // Cria uma instância do DAO para realizar a exclusão
        Tipo_VagaDAO tipo_VagaDAO = new Tipo_VagaDAO();

        // Tenta excluir o tipo de vaga
        try {
            tipo_VagaDAO.excluirTipo_Vaga(nomeTipo_Vaga);
        } catch (RuntimeException e) {
            // Se ocorrer um erro, redireciona para erro.jsp
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
            return; // Para garantir que a execução não continue
        }

        // Redireciona para a página de sucesso após a exclusão
        req.setAttribute("mensagem", "Tipo de vaga excluído com sucesso.");
        req.getRequestDispatcher("pages/tipoVaga.jsp").forward(req, res);
    }
}

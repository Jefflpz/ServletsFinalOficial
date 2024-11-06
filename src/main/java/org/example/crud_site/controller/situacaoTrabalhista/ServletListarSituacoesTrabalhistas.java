package org.example.crud_site.controller.situacaoTrabalhista;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.SituacaoTrabalhistaDAO;
import org.example.crud_site.model.SituacaoTrabalhista;

// Define a servlet que responde ao caminho /listarSituacao_Trabalhistas
@WebServlet("/listarSituacao_Trabalhistas")
public class ServletListarSituacoesTrabalhistas extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de situação trabalhista
        SituacaoTrabalhistaDAO situacao_TrabalhistaDAO = new SituacaoTrabalhistaDAO();

        // Obtém a lista de situações trabalhistas através do DAO
        List<SituacaoTrabalhista> situacoes_Trabalhistas = situacao_TrabalhistaDAO.listarSituacaoTrabalhista();

        // Define a lista de situações trabalhistas como um atributo da requisição
        req.setAttribute("listarSituacoes_Trabalhistas", situacoes_Trabalhistas);

        // Encaminha a requisição e a resposta para a página listarSituacao_Trabalhistas.jsp
        req.getRequestDispatcher("pages/situacaoTrabalhista.jsp").forward(req, res);
    }
}

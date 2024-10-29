package org.example.crud_site.controller.Situacao_Trabalhista;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Situacao_TrabalhistaDAO;
import org.example.crud_site.model.Situacao_Trabalhista;

// Define a servlet que responde ao caminho /listarSituacao_Trabalhistas
@WebServlet("/listarSituacao_Trabalhistas")
public class ServletListarSituacoes_Trabalhistas extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de situação trabalhista
        Situacao_TrabalhistaDAO situacao_TrabalhistaDAO = new Situacao_TrabalhistaDAO();

        // Obtém a lista de situações trabalhistas através do DAO
        List<Situacao_Trabalhista> situacoes_Trabalhistas = situacao_TrabalhistaDAO.listarSituacao_Trabalhista();

        // Define a lista de situações trabalhistas como um atributo da requisição
        req.setAttribute("situacoes_Trabalhistas", situacoes_Trabalhistas);

        // Encaminha a requisição e a resposta para a página listarSituacao_Trabalhistas.jsp
        req.getRequestDispatcher("/pages/situacaoTrabalhista.jsp").forward(req, res);
    }
}

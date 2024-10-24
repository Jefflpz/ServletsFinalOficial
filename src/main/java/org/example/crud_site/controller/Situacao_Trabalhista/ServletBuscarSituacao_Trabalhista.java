package org.example.crud_site.controller.Situacao_Trabalhista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Situacao_TrabalhistaDAO;
import org.example.crud_site.model.Situacao_Trabalhista;

import java.io.IOException;

@WebServlet("/buscarSituacaoTrabalhista")
public class ServletBuscarSituacao_Trabalhista extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Obtém o parâmetro "nomeSituacaoTrabalhista" da requisição
        String nomeSituacaoTrabalhista = req.getParameter("nomeSituacaoTrabalhista");

        // Cria uma instância do DAO para realizar a busca
        Situacao_TrabalhistaDAO situacaoTrabalhistaDAO = new Situacao_TrabalhistaDAO();
        Situacao_Trabalhista situacaoTrabalhista = situacaoTrabalhistaDAO.buscarSituacao_Trabalhista(nomeSituacaoTrabalhista);

        // Verifica se a situação trabalhista foi encontrada
        if (situacaoTrabalhista == null) {
            // Se não encontrada, define uma mensagem de erro e encaminha para uma página de erro
            req.setAttribute("erro", "Situação trabalhista não encontrada.");
            req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
            return;
        }

        // Se encontrada, adiciona a situação trabalhista como atributo na requisição
        req.setAttribute("situacaoTrabalhista", situacaoTrabalhista);
        // Encaminha para a página JSP que exibirá os dados da situação trabalhista
        req.getRequestDispatcher("exibirSituacaoTrabalhista.jsp").forward(req, res);
    }
}

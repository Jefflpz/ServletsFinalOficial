package org.example.crud_site.controller.Setor;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.model.Setor;

import java.io.IOException;

@WebServlet("/buscarSetor")
public class ServletBuscarSetor extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Obtém o parâmetro "nomeSetor" da requisição
        String nomeSetor = req.getParameter("nomeSetor");

        // Cria uma instância do DAO para realizar a busca
        SetorDAO setorDAO = new SetorDAO();
        Setor setor = setorDAO.buscarSetor(nomeSetor);

        // Verifica se o setor foi encontrado
        if (setor == null) {
            // Se não encontrado, define uma mensagem de erro e encaminha para uma página de erro
            req.setAttribute("erro", "Setor não encontrado.");
            req.getRequestDispatcher("erro.jsp").forward(req, res);
            return;
        }

        // Se encontrado, adiciona o setor como atributo na requisição
        req.setAttribute("setor", setor);
        // Encaminha para a página JSP que exibirá os dados do setor
        req.getRequestDispatcher("exibirSetor.jsp").forward(req, res);
    }
}

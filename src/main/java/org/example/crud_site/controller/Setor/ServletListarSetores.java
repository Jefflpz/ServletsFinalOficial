package org.example.crud_site.controller.Setor;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.model.Setor;

// Define a servlet que responde ao caminho /listarSetor
@WebServlet("/listarSetor")
public class ServletListarSetores extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de setores
        SetorDAO setorDAO = new SetorDAO();

        // Obtém a lista de setores através do DAO
        List<Setor> listaSetor = setorDAO.listarSetores();

        // Define a lista de setores como um atributo da requisição
        req.setAttribute("listarSetor", listaSetor);

        // Encaminha a requisição e a resposta para a página listarSetor.jsp
        req.getRequestDispatcher("pages/setor.jsp").forward(req, res);
    }

}

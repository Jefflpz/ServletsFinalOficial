package org.example.crud_site.controller.Setor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.model.Setor;

@WebServlet("/inserirSetor")
public class ServletInserirSetor extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do setor a partir da requisição.
        String nomeSetor = req.getParameter("nome");

        // Cria uma nova instância de Setor com o nome obtido.
        Setor setor = new Setor(nomeSetor);

        // Instancia o DAO para interagir com o banco de dados.
        SetorDAO setorDAO = new SetorDAO();

        // Chama o método para inserir o setor.
        boolean resultado = setorDAO.inserir(setor);

        // Se a operação falhar, redireciona para erro.jsp.
        if (resultado) {
            req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
        }

        // Redireciona para a página de sucesso (ou outra página apropriada).
        req.getRequestDispatcher("pages/setor.jsp").forward(req, res);
    }
}

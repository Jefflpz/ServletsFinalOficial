package org.example.crud_site.controller.Setor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.model.Setor;

@WebServlet("/alterarNomeSetor")
public class ServletAlterarNomeSetor extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém os parâmetros do setor a partir da requisição.
        String nomeSetorAntigo = req.getParameter("nome_antigo");
        String nomeSetorNovo = req.getParameter("nome_novo");

        // Cria uma nova instância de Setor com o nome antigo.
        Setor setor = new Setor(nomeSetorAntigo);


        // Instancia o DAO para interagir com o banco de dados.
        SetorDAO setorDAO = new SetorDAO();

        // Chama o método para alterar o nome do setor.
        boolean resultado = setorDAO.alterarNome(setor, nomeSetorNovo);

        // Se a operação falhar, redireciona para erro.jsp.
        if (!resultado) {
            req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
        }

        // Redireciona para a página de sucesso (ou outra página apropriada).
        req.getRequestDispatcher("pages/setor.jsp").forward(req, res);
    }
}

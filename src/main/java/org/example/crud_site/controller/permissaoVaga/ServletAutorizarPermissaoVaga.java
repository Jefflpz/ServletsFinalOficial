package org.example.crud_site.controller.permissaoVaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.example.crud_site.dao.PermissaoVagaDAO;

@WebServlet("/autorizarPermissao_Vaga")
public class ServletAutorizarPermissaoVaga extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o ID da vaga a partir da requisição.
        String idVagaParam = req.getParameter("id_vaga");
        UUID idVaga = UUID.fromString(idVagaParam);

        // Instancia o DAO para interagir com o banco de dados.
        PermissaoVagaDAO permissaoVagaDAO = new PermissaoVagaDAO();

        // Chama o método para autorizar a permissão.
        boolean resultado = permissaoVagaDAO.autorizarPermissao(idVaga);

        // Define um atributo para a requisição com o resultado da operação.
        req.setAttribute("resultado", resultado);

        // Redireciona para a página apropriada, com base no resultado da operação.
        if (resultado) {
            req.getRequestDispatcher("pages/permissaoCurso.jsp").forward(req, res);
        }

        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

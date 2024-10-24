package org.example.crud_site.controller.Permissao_Vaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.example.crud_site.dao.Permissao_VagaDAO;

@WebServlet("/autorizarPermissao_Vaga")
public class ServletAutorizarPermissao_Vaga extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o ID da vaga a partir da requisição.
        String idVagaParam = req.getParameter("id_vaga");
        UUID idVaga = UUID.fromString(idVagaParam);

        // Instancia o DAO para interagir com o banco de dados.
        Permissao_VagaDAO permissaoVagaDAO = new Permissao_VagaDAO();

        // Chama o método para autorizar a permissão.
        boolean resultado = permissaoVagaDAO.autorizarPermissao(idVaga);

        // Define um atributo para a requisição com o resultado da operação.
        req.setAttribute("resultado", resultado);

        // Redireciona para a página apropriada, com base no resultado da operação.
        if (resultado) {
            req.getRequestDispatcher("permissao_autorizada.jsp").forward(req, res);
        }

        req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
    }
}

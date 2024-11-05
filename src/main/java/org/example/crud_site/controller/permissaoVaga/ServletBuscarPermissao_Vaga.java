package org.example.crud_site.controller.permissaoVaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.PermissaoVagaDAO;
import org.example.crud_site.model.PermissaoVaga;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/buscarPermissao_Vaga")
public class ServletBuscarPermissao_Vaga extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o parâmetro "id" da requisição
        String idParam = req.getParameter("id");

        try {
            // Converte o parâmetro "id" para o tipo UUID
            UUID id = UUID.fromString(idParam);

            // Cria uma instância do DAO para realizar a busca
            PermissaoVagaDAO permissaoVagaDAO = new PermissaoVagaDAO();
            PermissaoVaga permissaoVaga = permissaoVagaDAO.buscarPermissaoVagaPorId(id);

            // Verifica se a permissão de vaga foi encontrada
            if (permissaoVaga == null) {
                // Define uma mensagem de erro e encaminha para a página de erro
                req.setAttribute("erro", "Permissão de vaga não encontrada.");
                req.getRequestDispatcher("pages/erro.jsp").forward(req, res);
                return;
            }

            // Adiciona o objeto permissaoVaga como atributo na requisição
            req.setAttribute("permissaoVaga", permissaoVaga);

            // Encaminha a requisição para a página de detalhes da permissão de vaga
            req.getRequestDispatcher("pages/permissaoVaga.jsp").forward(req, res);

        } catch (IllegalArgumentException e) {
            // Define uma mensagem de erro para o caso de UUID inválido
            req.setAttribute("erro", "ID inválido.");
            req.getRequestDispatcher("pages/erro.jsp").forward(req, res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);  // Redireciona GET para POST
    }
}

package org.example.crud_site.controller.Permissao_Vaga;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Permissao_VagaDAO;
import org.example.crud_site.model.Permissao_Vaga;

// Define a servlet que responde ao caminho /listarPermissao_Vaga
@WebServlet("/listarPermissao_Vaga")
public class ServletListarPermissoes_Vagas extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de permissões de vaga
        Permissao_VagaDAO permissao_VagaDAO = new Permissao_VagaDAO();

        // Obtém a lista de permissões de vaga através do DAO
        List<Permissao_Vaga> listaPermissao_Vaga = permissao_VagaDAO.listarPermissoes_Vaga();

        // Define a lista de permissões de vaga como um atributo da requisição
        req.setAttribute("listaPermissao_Vaga", listaPermissao_Vaga);

        // Encaminha a requisição e a resposta para a página permissao_Vaga.jsp
        req.getRequestDispatcher("permissao_Vaga.jsp").forward(req, res);
    }
}

package org.example.crud_site.controller.Tipo_Vaga;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Tipo_VagaDAO;
import org.example.crud_site.model.Tipo_Vaga;

// Define a servlet que responde ao caminho /listarTipo_Vaga
@WebServlet("/listarTipo_Vaga")
public class ServletListarTipos_Vagas extends HttpServlet {

    // Método que lida com requisições GET
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar dados de tipo de vaga
        Tipo_VagaDAO tipo_VagaDAO = new Tipo_VagaDAO();

        // Obtém a lista de tipos de vaga através do DAO
        List<Tipo_Vaga> tipos_Vaga = tipo_VagaDAO.listarTipo_Vaga();

        // Define a lista de tipos de vaga como um atributo da requisição
        req.setAttribute("tipos_Vaga", tipos_Vaga);

        // Encaminha a requisição e a resposta para a página listarTipo_Vaga.jsp
        req.getRequestDispatcher("listarTipo_Vaga.jsp").forward(req, res);
    }
}

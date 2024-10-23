package org.example.crud_site.controller.Tipo_Vaga;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Tipo_VagaDAO;
import org.example.crud_site.model.Tipo_Vaga;

import java.io.IOException;

@WebServlet("/buscarTipo_Vaga")
public class ServletBuscarTipo_Vaga {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Obtém o parâmetro "nomeTipo_Vaga" da requisição
        String nomeTipo_Vaga = req.getParameter("nomeTipo_Vaga");

        // Cria uma instância do DAO para realizar a busca
        Tipo_VagaDAO tipo_VagaDAO = new Tipo_VagaDAO();
        Tipo_Vaga tipo_Vaga = tipo_VagaDAO.buscarTipo_Vaga(nomeTipo_Vaga);

        // Verifica se o tipo de vaga foi encontrado
        if (tipo_Vaga == null) {
            // Se não encontrado, define uma mensagem de erro e encaminha para uma página de erro
            req.setAttribute("erro", "Tipo de vaga não encontrado.");
            req.getRequestDispatcher("erro.jsp").forward(req, res);
            return;
        }

        // Se encontrado, adiciona o tipo de vaga como atributo na requisição
        req.setAttribute("tipo_Vaga", tipo_Vaga);
        // Encaminha para a página JSP que exibirá os dados do tipo de vaga
        req.getRequestDispatcher("exibirTipo_Vaga.jsp").forward(req, res);
    }
}

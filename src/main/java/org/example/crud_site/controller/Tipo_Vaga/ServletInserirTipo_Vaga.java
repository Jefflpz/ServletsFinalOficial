package org.example.crud_site.controller.Tipo_Vaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Tipo_VagaDAO;
import org.example.crud_site.model.Tipo_Vaga;

import java.io.IOException;

@WebServlet("/inserirTipo_Vaga")
public class ServletInserirTipo_Vaga extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do tipo de vaga a partir da requisição
        String nomeTipo_Vaga = req.getParameter("nome_tipo_vaga");

        // Cria uma instância do modelo e do DAO para inserir o tipo de vaga
        Tipo_Vaga tipo_Vaga = new Tipo_Vaga(nomeTipo_Vaga);
        Tipo_VagaDAO tipo_VagaDAO = new Tipo_VagaDAO();

        // Insere o tipo de vaga e verifica se foi bem-sucedido
        boolean inserido = tipo_VagaDAO.inserirTipo_Vaga(tipo_Vaga);
        if (inserido) {
            // Redireciona para a página de sucesso
            req.setAttribute("mensagem", "Tipo de vaga inserido com sucesso.");
            req.getRequestDispatcher("sucesso.jsp").forward(req, res);
            return;
        }

        // Caso contrário, redireciona para a página de erro
        req.setAttribute("erro", "Falha ao inserir o tipo de vaga.");
        req.getRequestDispatcher("erro.jsp").forward(req, res);
    }
}

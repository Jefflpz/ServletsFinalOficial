package org.example.crud_site.controller.tipoVaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.TipoVagaDAO;
import org.example.crud_site.model.Tipo_Vaga;

import java.io.IOException;

@WebServlet("/atualizarTipo_Vaga")
public class ServletAlterarTipoVaga extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome atual e o novo nome do tipo de vaga a partir da requisição
        String nomeAtual = req.getParameter("nome_atual");
        String nomeNovo = req.getParameter("nome_novo");

        // Cria uma instância do modelo com o nome atual
        Tipo_Vaga tipo_Vaga = new Tipo_Vaga(nomeAtual);
        TipoVagaDAO tipo_VagaDAO = new TipoVagaDAO();

        // Atualiza o tipo de vaga e verifica se a operação foi bem-sucedida
        boolean atualizado = tipo_VagaDAO.alterarTipo_Vaga(tipo_Vaga, nomeNovo);
        if (atualizado) {
            // Redireciona para a página de sucesso
            req.setAttribute("mensagem", "Tipo de vaga atualizado com sucesso.");
            req.getRequestDispatcher("pages/tipoVaga.jsp").forward(req, res);
            return;
        }

        // Caso contrário, redireciona para a página de erro
        req.setAttribute("erro", "Falha ao atualizar o tipo de vaga.");
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

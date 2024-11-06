package org.example.crud_site.controller.tipoVaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.TipoVagaDAO;

import java.io.IOException;

@WebServlet("/excluirTipoVaga")
public class ServletExcluirTipoVaga extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o nome do tipo de vaga a partir da requisição
        String nomeTipo_Vaga = req.getParameter("nome");

        // Cria uma instância do DAO para realizar a exclusão
        TipoVagaDAO tipo_VagaDAO = new TipoVagaDAO();

        // Tenta excluir o tipo de vaga
        if (tipo_VagaDAO.excluirTipo_Vaga(nomeTipo_Vaga)){
            req.getRequestDispatcher("listarTipoVaga").forward(req, res);
        }
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

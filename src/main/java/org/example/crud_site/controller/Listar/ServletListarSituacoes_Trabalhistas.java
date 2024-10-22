package org.example.crud_site.controller.Listar;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Situacao_TrabalhistaDAO;
import org.example.crud_site.model.Situacao_Trabalhista;


@WebServlet("/listarSituacao_Trabalhistas")
public class ServletListarSituacoes_Trabalhistas {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Situacao_TrabalhistaDAO situacao_TrabalhistaDAO = new Situacao_TrabalhistaDAO();
        List<Situacao_Trabalhista> situacoes_Trabalhistas = situacao_TrabalhistaDAO.listarSituacao_Trabalhista();
        req.setAttribute("situacoes_Trabalhistas", situacoes_Trabalhistas);
        req.getRequestDispatcher("listarSituacao_Trabalhistas.jsp").forward(req, res);
    }
}

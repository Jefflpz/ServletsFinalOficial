package org.example.crud_site.controller.Setor;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.model.Setor;


@WebServlet("/listarSetor")
public class ServletListarSetores {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        SetorDAO setorDAO = new SetorDAO();
        List<Setor> listaSetor = setorDAO.listarSetores();
        req.setAttribute("listaSetor", listaSetor);
        req.getRequestDispatcher("listarSetor.jsp").forward(req, res);
    }
}

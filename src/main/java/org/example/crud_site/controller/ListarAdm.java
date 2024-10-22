package org.example.crud_site.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;
import org.example.crud_site.model.Adm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarAdm")
public class ListarAdm {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        AdmDAO admDAO = new AdmDAO();
        List<Adm> adms = admDAO.listarAdms();
        req.setAttribute("listarAdm", adms);
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}

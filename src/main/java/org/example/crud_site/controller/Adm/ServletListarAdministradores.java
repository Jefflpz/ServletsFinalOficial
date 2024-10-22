package org.example.crud_site.controller.Adm;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;
import org.example.crud_site.model.Adm;

import java.io.IOException;
import java.util.List;

@WebServlet("/listarAdm")
public class ServletListarAdministradores {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        AdmDAO admDAO = new AdmDAO();
        List<Adm> adms = admDAO.listarAdms();
        req.setAttribute("listarAdm", adms);
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}

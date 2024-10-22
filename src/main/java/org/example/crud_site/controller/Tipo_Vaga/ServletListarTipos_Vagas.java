package org.example.crud_site.controller.Tipo_Vaga;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Tipo_VagaDAO;
import org.example.crud_site.model.Tipo_Vaga;

@WebServlet("/listarTipo_Vaga")
public class ServletListarTipos_Vagas {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Tipo_VagaDAO tipo_VagaDAO = new Tipo_VagaDAO();
        List<Tipo_Vaga> tipos_Vaga = tipo_VagaDAO.listarTipo_Vaga();
        req.setAttribute("tipos_Vaga", tipos_Vaga);
        req.getRequestDispatcher("listarTipo_Vaga.jsp").forward(req, res);
    }
}

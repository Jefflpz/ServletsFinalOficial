package org.example.crud_site.controller.Listar;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Tipo_ArquivoDAO;
import org.example.crud_site.model.Tipo_Arquivo;

@WebServlet("/listarTipo_arquivo")
public class ServletListarTipos_Arquivos {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Tipo_ArquivoDAO tipo_ArquivoDAO = new Tipo_ArquivoDAO();
        List<Tipo_Arquivo> tipos_Arquivos = new Tipo_ArquivoDAO().listarTipo_Arquivo();
        req.setAttribute("tipos_Arquivos", tipos_Arquivos);
        req.getRequestDispatcher("listarTipo_arquivo.jsp").forward(req, res);

    }

}

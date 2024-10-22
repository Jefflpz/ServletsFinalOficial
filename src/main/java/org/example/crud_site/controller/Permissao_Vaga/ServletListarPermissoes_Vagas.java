package org.example.crud_site.controller.Permissao_Vaga;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.crud_site.dao.Permissao_VagaDAO;
import org.example.crud_site.model.Permissao_Vaga;


@WebServlet("/listarPermissao_Vaga")
public class ServletListarPermissoes_Vagas {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Permissao_VagaDAO permissao_VagaDAO = new Permissao_VagaDAO();
        List<Permissao_Vaga> listaPermissao_Vaga = permissao_VagaDAO.listarPermissoes_Vaga();
        req.setAttribute("listaPermissao_Vaga", listaPermissao_Vaga);
        req.getRequestDispatcher("permissao_Vaga.jsp").forward(req, res);

    }
}

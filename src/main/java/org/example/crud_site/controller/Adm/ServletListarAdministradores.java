package org.example.crud_site.controller.Adm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;
import org.example.crud_site.model.Adm;

import java.io.IOException;
import java.util.List;

// Define a servlet que responde ao caminho /listarAdm
@WebServlet("/listarAdm")
public class ServletListarAdministradores extends HttpServlet {

    // Método que lida com requisições GET
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Cria uma instância do DAO para acessar dados de administradores
        AdmDAO admDAO = new AdmDAO();
        // Obtém a lista de administradores através do DAO
        List<Adm> adms = admDAO.listarAdms();
        // Define a lista de administradores como um atributo da requisição
        req.setAttribute("listarAdm", adms);
        // Encaminha a requisição e a resposta para a página CrudAdm.jsp
        req.getRequestDispatcher("/pages/CrudAdm.jsp").forward(req, res);
    }
}

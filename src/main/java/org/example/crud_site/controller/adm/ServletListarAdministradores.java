package org.example.crud_site.controller.adm;

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

    // Método que lida com requisições Post
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Cria uma instância do DAO para acessar dados de administradores
        AdmDAO admDAO = new AdmDAO();
        List<Adm> listaAdms = admDAO.listarAdms();  // Este método deve estar implementado no AdmDAO
        request.setAttribute("listarAdm", listaAdms);

        // Encaminha para a página JSP que exibirá os dados do administrador
        request.getRequestDispatcher("pages/adm.jsp").forward(request, response);
    }
}

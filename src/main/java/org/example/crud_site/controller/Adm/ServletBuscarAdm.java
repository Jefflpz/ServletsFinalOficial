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

@WebServlet("/buscarAdm")
public class ServletBuscarAdm extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Obtém o parâmetro "username" da requisição
        String username = req.getParameter("username");

        // Cria uma instância do DAO para realizar a busca
        AdmDAO admDAO = new AdmDAO();
        Adm adm = admDAO.buscarAdm(username);

        // Verifica se o administrador foi encontrado
        if (adm == null) {
            // Se não encontrado, define uma mensagem de erro e encaminha para uma página de erro
            req.setAttribute("erro", "Administrador não encontrado.");
            req.getRequestDispatcher("/pages/erro.jsp").forward(req, res);
            return;
        }


        // Se encontrado, adiciona o administrador como atributo na requisição
        req.setAttribute("adm", adm);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);  // Redireciona GET para POST
    }
}

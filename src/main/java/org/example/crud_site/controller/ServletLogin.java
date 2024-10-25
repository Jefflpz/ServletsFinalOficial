package org.example.crud_site.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;
import org.example.crud_site.model.Adm;
import java.io.IOException;

@WebServlet(name = "Login administrador", value = "/login")
public class ServletLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String usuario = request.getParameter("usuario");

        String senha = request.getParameter("senha");

        AdmDAO admDAO = new AdmDAO();

        if(admDAO.validarLogin(usuario, senha)!=null){
            response.sendRedirect(request.getContextPath() + "/pages/CrudAdm.jsp");
        }else {
            request.setAttribute("erroLogin", "Usuário ou senha incorretos");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
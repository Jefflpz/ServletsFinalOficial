package org.example.crud_site.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;

import java.io.IOException;

@WebServlet(name = "Login administrador", value = "/login")
public class ServletLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String usuario = request.getParameter("usuario");

        String senha = request.getParameter("senha");

        if(verificarLogin(usuario, senha)){
            request.getRequestDispatcher("/listarAdm").forward(request, response);
        } else {
            request.setAttribute("erroLogin", "Usuário ou senha incorretos");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);  // Redireciona GET para POST
    }

    public boolean verificarLogin(String usuario, String senha){
        AdmDAO admDAO = new AdmDAO();
        return admDAO.buscarAdm(usuario, senha)!=null;
    }

}
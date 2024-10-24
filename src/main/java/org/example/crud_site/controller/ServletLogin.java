package org.example.crud_site.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;
import org.example.crud_site.model.Adm;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login administrador", value = "/login")
public class ServletLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String usuario = request.getParameter("usuario");

        String senha = request.getParameter("senha");

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try{
            if (verificarUsuario(usuario, senha)){
                out.println("success");
            }
        } catch (Exception e){
            out.println("error");
        }
        out.flush();
    }
    public boolean verificarUsuario(String usuario, String senha) {
        AdmDAO manipulador = new AdmDAO();
        Adm adm = manipulador.buscarAdm(usuario);
        return manipulador.buscarAdm(usuario) != null && adm.getSenha().equals(senha);
    }
}

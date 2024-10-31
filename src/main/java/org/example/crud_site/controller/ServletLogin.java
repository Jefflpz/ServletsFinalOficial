package org.example.crud_site.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.AdmDAO;
import org.example.crud_site.dao.HashSenha;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Login administrador", value = "/login")
public class ServletLogin extends HttpServlet {
    private final Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");

        StringBuilder requestBody = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"success\": false, \"message\": \"Erro no servidor\"}");
            e.printStackTrace();
            return;
        }

        // Lê o JSON por meio da classe Login
        Login adm = gson.fromJson(requestBody.toString(), Login.class);

        // Criando o hashing da senha
        String senhaHash;

        try {
            HashSenha hashSenha = new HashSenha(adm.getSenha());
            senhaHash = hashSenha.getSenha();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

//        String user = (String) request.getAttribute("usuario");
//        String senha = (String) request.getAttribute("senha");
//        System.out.println("Usuario: " + user);
//        System.out.println("Senha: " + senha);

        try {
            if (verificarLogin(adm.getAdm(), senhaHash)) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("{\"success\":true}");
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"success\": false, \"message\": \"Usuário ou senha incorretos\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"success\": false, \"message\": \"Erro no servidor\"}");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);  // Redireciona GET para POST
    }

    private boolean verificarLogin(String usuario, String senha) {
        AdmDAO admDAO = new AdmDAO();
        return admDAO.buscarAdm(usuario, senha) != null;
    }

    private static class Login {
        private String adm;
        private String senha;

        public String getAdm() {
            return this.adm;
        }

        public String getSenha() {
            return this.senha;
        }
    }

}
package org.example.crud_site.controller.adm;

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

@WebServlet("/inserirAdm")
public class ServletInserirAdm extends HttpServlet {
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

        // Cria uma instância do DAO para realizar a inserção
        AdmDAO admDAO = new AdmDAO();

        Cadastro adm = gson.fromJson(requestBody.toString(), Cadastro.class);

        // Criando o hashing da senha
        String senhaHash;

        try {
            HashSenha hashSenha = new HashSenha(adm.getSenha());
            senhaHash = hashSenha.getSenha();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try {
            if (admDAO.inserirAdm(adm.getAdm(), senhaHash)) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("{\"success\":true}");
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"success\": false, \"message\": \"Não foi possível inserir o administrador.\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"success\": false, \"message\": \"Erro no servidor\"}");
            e.printStackTrace();
        }

    }

    private static class Cadastro {
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

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
import java.util.UUID;

@WebServlet("/alterarLoginAdm")
public class ServletAlterarAdm extends HttpServlet {
    private final Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        // Obtém os parâmetros da requisição
//        String username = req.getParameter("username");
//        String senha = req.getParameter("senha");

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
            
            if ( adm.getSenha() == null && adm.getUsername() != null ){
                admDAO.alterarLoginAdm(adm.getUsername(), adm.getId());
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("{\"success\":true}");
            }
            else if ( adm.getSenha() != null && adm.getUsername() == null ){
                admDAO.alterarSenhaAdm(adm.getSenha(), adm.getId());
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("{\"success\":true}");
            }
            else{
                if ( (admDAO.alterarLoginAdm(adm.getUsername(), adm.getId()) && (admDAO.alterarSenhaAdm(senhaHash, adm.getId())))){
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("{\"success\":true}");
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"success\": false, \"message\": \"Erro no servidor\"}");
            e.printStackTrace();
        }
    }

    private static class Cadastro {
        private String username;
        private String senha;
        private UUID id;


        public UUID getId() {
            return this.id;
        }
        public String getUsername() {
            return this.username;
        }

        public String getSenha() {
            return this.senha;
        }
    }
}

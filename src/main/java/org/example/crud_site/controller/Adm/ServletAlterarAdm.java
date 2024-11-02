package org.example.crud_site.controller.Adm;

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
            if ( admDAO.alterarAdm(adm.getUsername(), senhaHash, adm.getId()) ) {
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

        // Verifica se a inserção foi bem-sucedida
//        if (resultado) {
//            // Se a inserção foi bem-sucedida, redireciona para a página de sucesso
//            req.getRequestDispatcher("/pages/CrudAdm.jsp").forward(req, res);
//            return; // Para evitar a execução do código abaixo
//        }
//
//        // Se não for bem-sucedida, define uma mensagem de erro e encaminha para uma página de erro
//        req.setAttribute("erro", "Falha ao inserir o administrador.");
//        req.getRequestDispatcher("/pages/errorPage.jsp").forward(req, res);
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

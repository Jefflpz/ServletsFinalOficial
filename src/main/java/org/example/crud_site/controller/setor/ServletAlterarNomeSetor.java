package org.example.crud_site.controller.setor;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

import org.example.crud_site.dao.SetorDAO;
import org.example.crud_site.model.Setor;

@WebServlet("/alterarNomeSetor")
public class ServletAlterarNomeSetor extends HttpServlet {
    private final Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        // Instancia o DAO para interagir com o banco de dados.
        SetorDAO setorDAO = new SetorDAO();

        EditarSetor editarSetor = gson.fromJson(requestBody.toString(), EditarSetor.class);

        try {
            if (setorDAO.alterarNomeSetor(editarSetor.getUuid(), editarSetor.getSetor())) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("{\"success\":true}");
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"success\": false, \"message\": \"Não foi possível alterar o nome do setor.\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"success\": false, \"message\": \"Erro no servidor\"}");
            e.printStackTrace();
        }
    }

    private static class EditarSetor {
        private String setor;
        private UUID uuid;

        public String getSetor() {
            return this.setor;
        }

        public UUID getUuid() {
            return this.uuid;
        }
    }
}

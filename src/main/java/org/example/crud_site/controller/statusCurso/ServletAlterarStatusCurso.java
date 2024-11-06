package org.example.crud_site.controller.statusCurso;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.StatusCursoDAO;
import org.example.crud_site.model.StatusCurso;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/alterarStatusCurso")
public class ServletAlterarStatusCurso extends HttpServlet {
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

        // Cria uma instância do DAO para realizar a alteração
        StatusCursoDAO statusCursoDAO = new StatusCursoDAO();

        EditarStatusCurso statusCurso = gson.fromJson(requestBody.toString(), EditarStatusCurso.class);

        try {
            if (statusCursoDAO.alterarNomeStatusCurso(statusCurso.getUuid(), statusCurso.getStatusCurso())) {
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

    private static class EditarStatusCurso {
        private String statusCurso;
        private UUID uuid;

        public String getStatusCurso() {
            return this.statusCurso;
        }

        public UUID getUuid() {
            return this.uuid;
        }
    }
}

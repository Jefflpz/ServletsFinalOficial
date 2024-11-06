package org.example.crud_site.controller.tipoVaga;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.TipoVagaDAO;
import org.example.crud_site.model.TipoVaga;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/atualizarTipoVaga")
public class ServletAlterarTipoVaga extends HttpServlet {
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

        TipoVagaDAO tipoVagaDAO = new TipoVagaDAO();

        EditarTipoVaga editarTipoVaga = gson.fromJson(requestBody.toString(), EditarTipoVaga.class);

        try {
            if (tipoVagaDAO.alterarTipoVaga(editarTipoVaga.getUuid(), editarTipoVaga.getTipoVaga())) {
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

    private static class EditarTipoVaga {
        private String tipoVaga;
        private UUID uuid;

        public String getTipoVaga() {
            return this.tipoVaga;
        }

        public UUID getUuid() {
            return this.uuid;
        }
    }
}

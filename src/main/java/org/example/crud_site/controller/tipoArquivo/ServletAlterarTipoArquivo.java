package org.example.crud_site.controller.tipoArquivo;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.TipoArquivoDAO;
import org.example.crud_site.model.TipoArquivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/alterarTipoArquivo")
public class ServletAlterarTipoArquivo extends HttpServlet {
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
        TipoArquivoDAO tipoArquivoDAO = new TipoArquivoDAO();

        EditarTipoArquivo statusCurso = gson.fromJson(requestBody.toString(), EditarTipoArquivo.class);

        try {
            if (tipoArquivoDAO.alterarNomeTipoArquivo(statusCurso.getUuid(), statusCurso.getTipoArquivo())) {
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

    private static class EditarTipoArquivo {
        private String tipoArquivo;
        private UUID uuid;

        public String getTipoArquivo() {
            return this.tipoArquivo;
        }

        public UUID getUuid() {
            return this.uuid;
        }
    }
}

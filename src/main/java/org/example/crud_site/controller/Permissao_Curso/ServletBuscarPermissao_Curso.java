package org.example.crud_site.controller.Permissao_Curso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crud_site.dao.Permissao_CursoDAO;
import org.example.crud_site.model.PermissaoCurso;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/buscarPermissaoCurso")
public class ServletBuscarPermissao_Curso extends HttpServlet {

    // Método que trata requisições do tipo POST
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Obtém o parâmetro "id" da requisição
        String idParam = req.getParameter("id");

        // Converte o parâmetro "id" para UUID
        UUID id = UUID.fromString(idParam);

        // Cria uma instância do DAO para realizar a busca
        Permissao_CursoDAO permissaoCursoDAO = new Permissao_CursoDAO();
        PermissaoCurso permissaoCurso = permissaoCursoDAO.buscarPermissao_CursoPorId(id);

        // Verifica se a permissão de curso foi encontrada
        if (permissaoCurso == null) {
            // Se não encontrada, define uma mensagem de erro e encaminha para uma página de erro
            req.setAttribute("erro", "Permissão de curso não encontrada.");
            req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
            return;
        }


        // Se encontrada, adiciona a permissão de curso como atributo na requisição
        req.setAttribute("permissaoCurso", permissaoCurso);

        // Encaminha para uma página de sucesso ou exibição dos detalhes da permissão de curso
        req.getRequestDispatcher("pages/permissaoCurso.jsp").forward(req, res);
    }

    // Método que trata requisições do tipo GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redireciona GET para POST
        doPost(request, response);
    }
}

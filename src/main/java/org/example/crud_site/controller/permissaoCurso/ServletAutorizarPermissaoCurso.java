package org.example.crud_site.controller.permissaoCurso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.example.crud_site.dao.PermissaoCursoDAO;

@WebServlet("/autorizarPermissao_Curso")
public class ServletAutorizarPermissaoCurso extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o ID do curso a partir da requisição.
        String idCursoParam = req.getParameter("id_curso");
        UUID idCurso;
        try {
            idCurso = UUID.fromString(idCursoParam);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "ID do curso inválido.");
            req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
            return;
        }

        // Instancia o DAO para interagir com o banco de dados.
        PermissaoCursoDAO permissaoCursoDAO = new PermissaoCursoDAO();

        // Chama o método para autorizar a permissão.
        boolean resultado = permissaoCursoDAO.autorizarPermissao(idCurso);

        // Define um atributo para a requisição com o resultado da operação.
        req.setAttribute("resultado", resultado);

        // Redireciona para a página apropriada, com base no resultado da operação.
        if (resultado) {
            req.getRequestDispatcher("permissao_autorizada.jsp").forward(req, res);
        }
        // Redireciona para a página de erro, caso a operação não tenha sido bem-sucedida.
        req.getRequestDispatcher("pages/errorPage.jsp").forward(req, res);
    }
}

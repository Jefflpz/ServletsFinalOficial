package org.example.crud_site.controller.Permissao_Curso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.example.crud_site.dao.Permissao_CursoDAO;

@WebServlet("/negarPermissao")
public class ServletNegarPermissao_Curso {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Obtém o ID do curso a partir da requisição.
        String idCursoParam = req.getParameter("id_curso");
        UUID idCurso = UUID.fromString(idCursoParam);

        // Instancia o DAO para interagir com o banco de dados.
        Permissao_CursoDAO permissaoCursoDAO = new Permissao_CursoDAO();

        // Chama o método para negar a permissão.
        boolean resultado = permissaoCursoDAO.negarPermissao(idCurso);

        // Define um atributo para a requisição com o resultado da operação.
        req.setAttribute("resultado", resultado);

        // Verifica se a operação foi bem-sucedida e redireciona para a página apropriada.
        if (resultado) {
            req.getRequestDispatcher("permissao_negada.jsp").forward(req, res);
        }

        // Redireciona para a página de erro, caso a operação não tenha sido bem-sucedida.
        req.getRequestDispatcher("erro.jsp").forward(req, res);
    }
}

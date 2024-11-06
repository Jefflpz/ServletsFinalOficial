<%@ page import="org.example.crud_site.model.StatusCurso" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>CRUD status curso</title>
  <link rel="stylesheet" href="css/statusCurso.css">
</head>
<body>

  <aside class="sidebar">
    <div class="titulo">
        <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
    </div>
    <nav>
        <ul>
            <li><a href="#"><img src="img/casa.png">Home</a></li>
            <li><a href="listarAdm">Adm</a></li>
            <li><a href="listarPermissao_Vaga">Permissão Vaga</a></li>
            <li><a href="listarPermissao_Curso">Permissão Curso</a></li>
            <li><a href="listarSetor">Setores</a></li>
            <li><a href="listarTipo_arquivo">Tipo de arquivo</a></li>
            <li><a href="listarTipo_Vaga">Tipo de vaga</a></li>
            <li><a href="listarSituacao_Trabalhistas">Situação trabalhista</a></li>
            <div class="elemento"></div>
            <li class="selecionado"><a href="#">Status curso</a></li>
            <li><a href="index.html" class="logout">Sair</a></li>
        </ul>
    </nav>
    </aside>


    <div class="container">
        <main class="main-content">
            <div class="titulo">
                <h1>Status curso</h1>
                <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
                <button class="inserir-status-curso">Inserir Status Curso </button>
            </div>

            <!-- Barra de Filtro -->
            <div id="filtrar-bar" style="display: none;">
                <form action="#">
                    <select name="filter-field">
                        <option value="">Escolher campo</option>
                        <option value="todos">Todos</option>
                        <option value="registro-filtro">Registro</option>
                        <option value="status-filtro">Status Curso</option>
                    </select>

                    <input type="text" name="search" placeholder="Pesquisar...">

                    <button type="submit">Aplicar</button>
                </form>
            </div>

            <div class="grid-container">
                <div class="grid-header registro">Registro</div>
                <div class="grid-header Nome">Status do curso</div>
                <div class="grid-header ações">Ações</div>


                <%
                    List<StatusCurso> lista = (List<StatusCurso>) request.getAttribute("listarStatus_curso");

                    if (!lista.isEmpty()) {
                        for (int i = 0; i < lista.size(); i++) {
                %>
                <div class="grid-item registro"><%= i+1 %></div>
                <div class="grid-item username"><%= lista.get(i).getNome() %></div>
                <div class="grid-item">
                    <button class="action edit">
                        <img src="img/lapis.png" alt="editar status curso"></button>
                    <form action="excluirStatusCurso" method="get">
                        <input type="hidden" id="nome" name="nome" value="<%=lista.get(i).getNome()%>">
                        <button class="action delete"><img src="img/lixo.png" alt="excluir status curso"></button>
                    </form>
                </div>
                <%
                    }
                } else {
                %>
                <div class="grid-item">Nenhum registro encontrado.</div>
                <%
                        System.out.println("Nenhum registro encontrado.");
                    }
                %>
            </div>

        </main>


        <!-- AQUI -->

        <div id="popupID" style="display: none;">
            <div class="popup">
                <form action="inserirStatus_Curso" class="registrar" method="post">
                    <div class="bloco-titulo">
                        <h2 class="titulo">Inserir Status Curso</h2>
                    </div>
                    <div class="form-group">
                        <label for="cadastrar" class="label">Status curso:</label>
                        <input type="text" id="cadastrar" name="cadastrar" placeholder="Em andamento" class="input" required />
                    </div>

                    <button type="submit" class="b bt-registrar">Cadastrar</button>
                    <button type="button" class="b bt-cancelar">Cancelar</button>
                </form>
            </div>
        </div>

        <div id="popupIDStatusCurso" style="display: none;">
            <div class="popup">
                <form action="" class="registrar">
                    <div class="bloco-titulo">
                        <h2 class="titulo">Editar status curso</h2>
                    </div>
                    <div class="form-group">
                        <label for="editar" class="label">Status curso:</label>
                        <input type="text" id="editar" name="editar" placeholder="cancelado" class="input" required />
                    </div>

                    <button type="submit" class="b bt-registrar">Cadastrar</button>
                    <button type="button" class="b bt-cancelar-edit">Cancelar</button>
                </form>
            </div>
        </div>
    </div>

  <script src="js/statusCurso.js"></script>
</body>
</html>
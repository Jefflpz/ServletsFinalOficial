<%@ page import="java.util.List" %>
<%@ page import="org.example.crud_site.model.TipoVaga" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Adm</title>
    <link rel="stylesheet" href="css/tipoVaga.css">
</head>
<body>

<aside class="sidebar">
    <div class="titulo">
        <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
    </div>
    <nav>
        <ul>
            <li><a href="#"><img src="img/casa.png">Home</a></li>
            <li ><a href="listarAdm">Adm</a></li>
            <li><a href="listarPermissaoVaga">Permissão Vaga</a></li>
            <li><a href="listarPermissaoCurso">Permissão Curso</a></li>
            <li><a href="listarSetor">Setores</a></li>
            <li ><a href="listarTipoArquivo">Tipo de arquivo</a></li>
            <div class="elemento"></div>
            <li class="selecionado"><a href="#">Tipo de vaga</a></li>
            <li><a href="listarSituacaoTrabalhista">Situação trabalhista</a></li>
            <li><a href="listarStatusCurso">Status curso</a></li>
            <li><a href="index.html" class="logout">Sair</a></li>
        </ul>
    </nav>
</aside>

<div class="container">
    <main class="main-content">
        <div class="titulo">
            <h1>Tipo da vaga</h1>
            <button class="filtrar">Filtrar <span class="pesquisa"></span></button>
            <button class="inserir-vaga">Inserir Vaga</button>
        </div>

        <!-- Barra de Filtro -->
        <div id="filtrar-bar" style="display: none;">
            <form action="#">
                <select name="filter-field">
                    <option value="">Escolher campo</option>
                    <option value="todos">Todos</option>
                    <option value="registro-filtro">Registro</option>
                    <option value="tipoVaga-filtro">Tipo de Vaga</option>
                </select>

                <input type="text" name="search" placeholder="Pesquisar...">

                <button type="submit">Aplicar</button>
            </form>
        </div>

        <div class="grid-container">
            <div class="grid-header registro">Registro</div>
            <div class="grid-header username">Tipo da vaga</div>
            <div class="grid-header acoes">Ações</div>

            <%
                List<TipoVaga> lista = (List<TipoVaga>) request.getAttribute("listarTipo_Vaga");

                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
            %>
            <div class="grid-item registro"><%= i+1 %></div>
            <div class="grid-item username"><%= lista.get(i).getNome() %></div>
            <div class="grid-item">
                <button class="action edit"
                        data-nomeTipoVaga="<%= lista.get(i).getNome() %>"
                        data-uuid="<%= lista.get(i).getId() %>">
                    <img src="img/lapis.png" alt="editar tipo vaga"></button>
                <form action="excluirTipoVaga" method="get">
                    <input type="hidden" id="nome" name="nome" value="<%=lista.get(i).getNome()%>">
                    <button id="delete" class="action delete"><img src="img/lixo.png" alt="excluir tipo vaga"></button>
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
            <form action="inserirTipo_Vaga" class="registrar" method="post">
                <div class="bloco-titulo">
                    <h2 class="titulo">Inserir tipo vaga</h2>
                </div>
                <div class="form-group">
                    <label for="cadastrar" class="label">Tipo vaga:</label>
                    <input type="text" id="cadastrar" name="cadastrar" placeholder="Em andamento" class="input" required />
                </div>

                <button type="submit" class="b bt-registrar">Cadastrar</button>
                <button type="button" class="b bt-cancelar">Cancelar</button>
            </form>
        </div>
    </div>

    <div id="popupIDTipoVaga" style="display: none;">
        <div class="popup">
            <form action="atualizarTipoVaga" class="registrar" id="editarTipoVaga">
                <div class="bloco-titulo">
                    <h2 class="titulo">Editar tipo vaga</h2>
                </div>
                <div class="form-group">
                    <label for="editar" class="label">Tipo vaga:</label>
                    <input type="text" id="editar" name="editar" placeholder="cancelado" class="input" required />
                </div>

                <button type="submit" class="b bt-registrar">Cadastrar</button>
                <button type="button" class="b bt-cancelar-edit">Cancelar</button>
            </form>
        </div>
    </div>
</div>
</div>
</div>
<script src="js/tipoVaga.js"></script>
</body>
</html>
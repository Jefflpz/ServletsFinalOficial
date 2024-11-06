<%@ page import="java.util.List" %>
<%@ page import="org.example.crud_site.model.SituacaoTrabalhista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD status curso</title>
    <link rel="stylesheet" href="css/situacaoTrabalhista.css">
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
            <div class="elemento"></div>
            <li class="selecionado"><a href="#">Situação trabalhista</a></li>
            <li><a href="listarStatus_Curso">Status curso</a></li>
            <li><a href="index.html" class="logout">Sair</a></li>
        </ul>
    </nav>
  </aside>


    <div class="container">
        <main class="main-content">
            <div class="titulo">
                <h1>Situação trabalhista</h1>
                <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
                <button class="inserir-situacao">Inserir Situação Trabalhista</button>
            </div>

            <!-- Barra de Filtro -->
            <div id="filtrar-bar" style="display: none;">
                <form action="#">
                    <select name="filter-field">
                        <option value="">Escolher campo</option>
                        <option value="todos">Todos</option>
                        <option value="registro-filtro">Registro</option>
                        <option value="situacao-filtro">Situação Trabalhistas</option>
                    </select>

                    <input type="text" name="search" placeholder="Pesquisar...">

                    <button type="submit">Aplicar</button>
                </form>
            </div>

            <div class="grid-container">
                <div class="grid-header registro">Registro</div>
                <div class="grid-header username">Situação Trabalhista</div>
                <div class="grid-header acoes">Ações</div>


                <%
                    List<SituacaoTrabalhista> lista = (List<SituacaoTrabalhista>) request.getAttribute("listarSituacoes_Trabalhistas");

                    if (!lista.isEmpty()) {
                        for (int i = 0; i < lista.size(); i++) {
                %>
                <div class="grid-item registro"><%= i+1 %></div>
                <div class="grid-item username"><%= lista.get(i).getNome() %></div>
                <div class="grid-item">
                    <button class="action edit">
                        <img src="img/lapis.png" alt="editar situacao trabalhista"></button>
                    <form action="excluirSituacaoTrabalhista" method="get">
                        <input type="hidden" id="nome" name="nome" value="<%=lista.get(i).getNome()%>">
                        <button class="action delete"><img src="img/lixo.png" alt="excluir situacao trabalhista"></button>
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
                <form action="inserirSituacaoTrabalhista" class="registrar" method="post">
                    <div class="bloco-titulo">
                        <h2 class="titulo">Inserir Situação Trabalhista</h2>
                    </div>
                    <div class="form-group">
                        <label for="cadastrar" class="label">Situação trabalhista:</label>
                        <input type="text" id="cadastrar" name="cadastrar" placeholder="avnadmin" class="input" required />
                    </div>

                    <button type="submit" class="b bt-registrar">Cadastrar</button>
                    <button type="button" class="b bt-cancelar">Cancelar</button>
                </form>
            </div>
        </div>

        <div id="popupIDSituacaoTrabalhista" style="display: none;">
            <div class="popup">
                <form action="alterarSituacaoTrabalhista" class="registrar" method="post">
                    <div class="bloco-titulo">
                        <h2 class="titulo">Editar situação trabalhista</h2>
                    </div>
                    <div class="form-group">
                        <label for="editar" class="label">Situação trabalhista:</label>
                        <input type="text" id="editar" name="editar" placeholder="avnadmin" class="input" required />
                    </div>

                    <button type="submit" class="b bt-registrar">Cadastrar</button>
                    <button type="button" class="b bt-cancelar-edit">Cancelar</button>

                </form>
            </div>
        </div>

        <!-- AQUI -->
    </div>


<script src="js/situacaoTrabalhista.js"></script>
</body>
</html>
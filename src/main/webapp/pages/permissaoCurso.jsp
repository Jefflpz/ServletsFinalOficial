<%@ page import="org.example.crud_site.model.PermissaoCurso" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crud Permissao vaga</title>
    <link rel="stylesheet" href="css/permissaoCurso.css">
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
            <li class="selecionado"><a href="#">Permissão Curso</a></li>
            <li><a href="listarPermissao_Vaga">Permissão Vaga</a></li>
            <li><a href="listarSetor">Setores</a></li>
            <li><a href="listarSituacao_Trabalhistas">Situação trabalhista</a></li>
            <li><a href="listarStatus_Curso">Status curso</a></li>
            <li><a href="listarTipo_arquivo">Tipo de arquivo</a></li>
            <li><a href="listarTipo_Vaga">Tipo de vaga</a></li>
            <li><a href="index.html" class="logout">Sair</a></li>
        </ul>
    </nav>

</aside>

<div class="container">
    <main class="main-content">
        <div class="titulo">
            <h1>Crud Permissao Curso</h1>
            <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
        </div>

        <!-- Barra de Filtro -->
        <div id="filter-bar" style="display: none;">
            <form action="#" method="GET">
                <select name="filter-field">
                    <option value="">Escolher campo</option>
                    <option value="Todos">Todos</option>
                    <option value="Registro-filtro">Registro</option>
                    <option value="id_conta-filtro">id_conta</option>
                    <option value="id_vaga-filtro">id_vaga</option>
                </select>
                <input type="text" name="search" placeholder="Pesquisar...">
                <button type="submit">Aplicar</button>
            </form>
        </div>

        <div class="grid-container">
            <div class="grid-header registro">Registro</div>
            <div class="grid-header id_conta">dt_solicitação</div>
            <div class="grid-header id_conta">Permissão</div>
            <div class="grid-header acoes">Permissão</div>

            <%
                List<PermissaoCurso> lista = (List<PermissaoCurso>) request.getAttribute("listaPermissao_Curso");

                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
            %>
            <div class="grid-item registro"><%= i+1 %></div>
            <div class="grid-item username"><%= lista.get(i).getDt_solicitaco() %></div>
            <div class="grid-item"> <%= lista.get(i).getPermissao() %></div>
            <div class="grid-item">
                <button class="action view"><img src="img/olho.png" alt=""></button>
                <button class="action view"><img src="img/olho.png" alt=""></button>
                <button class="action delete"><img src="img/lixo.png" alt=""></button>
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
</div>
</body>
<script src="js/permissaoCurso.js"></script>

</html>
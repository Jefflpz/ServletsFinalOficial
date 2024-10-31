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
            <div class="grid-header id_conta">id_conta</div>
            <div class="grid-header id_conta">id_vaga</div>
            <div class="grid-header acoes">Permissão</div>

            <div class="grid-item item-registro">1</div>
            <div class="grid-item item-id_conta">24</div>
            <div class="grid-item item-id_vaga">31</div>
            <div class="grid-item">
                <button class="action view"><img src="olho.png" alt=""></button>
            </div>

            <div class="grid-item item-registro">2</div>
            <div class="grid-item item-id_conta">12</div>
            <div class="grid-item item-id_vaga">23</div>
            <div class="grid-item">
                <button class="action view"><img src="olho.png" alt=""></button>
            </div>

        </div>
    </main>
</div>
</body>
<script src="js/permissaoCurso.js"></script>

</html>
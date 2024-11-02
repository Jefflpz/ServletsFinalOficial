<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Permissão Curso</title>
    <link rel="stylesheet" href="css/permissaoCurso.css">
</head>
<body>

<aside class="sidebar">
    <div class="titulo">
        <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
    </div>
    <nav>
        <ul>
            <li><a href="#"><img src="img/house.png" alt="Home">Home</a></li>
            <li><a href="listarAdm">Adm</a></li>
            <li class="selecionado"><a href="listarPermissao_Curso">Permissão Curso</a></li>
            <li><a href="listarPermissao_Vaga">Permissão Vaga</a></li>
            <li><a href="listarSetor">Setores</a></li>
            <li><a href="listarSituacao_Trabalhistas">Situação Trabalhista</a></li>
            <li><a href="listarStatus_Curso">Status Curso</a></li>
            <li><a href="listarTipo_arquivo">Tipo de Arquivo</a></li>
            <li><a href="listarTipo_Vaga">Tipo de Vaga</a></li>
            <li><a href="index.html" class="logout">Sair</a></li>
        </ul>
    </nav>
    <button class="logout">Sair</button>
</aside>

<div class="container">
    <main class="main-content">
        <div class="titulo">
            <h1>Permissão Curso</h1>
            <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
        </div>

        <!-- Barra de Filtro -->
        <div id="filter-bar" style="display: none;">
            <form action="#" method="GET">
                <select name="filter-field">
                    <option value="">Escolher campo</option>
                    <option value="Todos">Todos</option>
                    <option value="Registro-filtro">Registro</option>
                    <option value="id_conta-filtro">ID Conta</option>
                    <option value="Curso-filtro">ID Curso</option>
                </select>
                <input type="text" name="search" placeholder="Pesquisar...">
                <button type="submit">Aplicar</button>
            </form>
        </div>

        <div class="grid-container">
            <div class="grid-header registro">Registro</div>
            <div class="grid-header id_conta">ID Conta</div>
            <div class="grid-header id_Curso">ID Curso</div>
            <div class="grid-header acoes">Permissão</div>

            <div class="grid-item item-registro">1</div>
            <div class="grid-item item-id_conta">24</div>
            <div class="grid-item item-id_Curso">31</div>
            <div class="grid-item">
                <button class="action view"><img src="img/olho.png" alt="Visualizar"></button>
            </div>

            <div class="grid-item item-registro">2</div>
            <div class="grid-item item-id_conta">12</div>
            <div class="grid-item item-id_Curso">23</div>
            <div class="grid-item">
                <button class="action view"><img src="img/olho.png" alt="Visualizar"></button>
            </div>

        </div>

        <div id="popupID" style="display: none;">
            <div class="popup">
                <div class="popup-header">
                    <h2 class="titulo">Informações da Curso</h2>
                    <button class="close-button">✖</button>
                </div>
                <form action="#" class="curso-form">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="uuid" class="label">UUID:</label>
                            <input type="text" id="uuid" name="uuid" class="input" required />
                        </div>
                        <div class="form-group">
                            <label for="id_tipo" class="label">ID Tipo:</label>
                            <input type="text" id="id_tipo" name="id_tipo" class="input" required />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nome" class="label">Nome:</label>
                            <input type="text" id="nome" name="nome" class="input" required />
                        </div>
                        <div class="form-group">
                            <label for="id_empresa" class="label">ID Empresa:</label>
                            <input type="text" id="id_empresa" name="id_empresa" class="input" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descricao" class="label">Descrição:</label>
                        <textarea id="descricao" name="descricao" class="input" maxlength="500"></textarea>
                        <span class="char-count">0/500</span>
                    </div>
                    <div class="form-buttons">
                        <button type="submit" class="b bt-confirmar"><img src="img/correto.png" alt="Confirmar"></button>
                        <button type="button" class="b bt-cancelar"><img src="img/lixo.png" alt="Cancelar"></button>
                    </div>
                </form>
            </div>
        </div>

    </main>
</div>

<script src="js/permissaoCurso.js"></script>
</body>
</html>

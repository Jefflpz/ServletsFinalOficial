<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Crud Permissao curso</title>
  <link rel="stylesheet" href="css/permissaoCurso.css">
</head>
<body>

    <aside class="sidebar">
        <div class="titulo">
            <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
        </div>
        <nav>
            <ul>
                <li><a href="#"><img src="img/casa.png" alt="Home">Home</a></li>
                <li><a href="listarAdm">Adm</a></li>
                <li><a href="listarPermissao_Vaga">Permissão Vaga</a></li>
                <div class="elemento"></div>
                <li class="selecionado"><a href="#">Permissão Curso</a></li>
                <li><a href="listarSetor">Setores</a></li>
                <li><a href="listarTipo_arquivo">Tipo de arquivo</a></li>
                <li><a href="listarTipo_Vaga">Tipo de vaga</a></li>
                <li><a href="listarSituacao_Trabalhistas">Situação trabalhista</a></li>
                <li><a href="listarStatus_Curso">Status curso</a></li>
                <li><a href="index.html" class="logout">Sair</a></li>
            </ul>
        </nav>
    </aside>


    <div class="container">
        <main class="main-content">
            <div class="titulo">
                <h1>Permissao curso</h1>
                <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
            </div>

            <!-- Barra de Filtro -->
            <div id="filter-bar" style="display: none;">
                <form action="#" method="GET">
                    <select name="filter-field">
                        <option value="">Escolher campo</option>
                        <option value="todos">Todos</option>
                        <option value="registro-filtro">Registro</option>
                        <option value="id_conta-filtro">id_conta</option>
                        <option value="id_curso-filtro">id_curso</option>
                    </select>

                    <input type="text" name="search" placeholder="Pesquisar...">

                    <button type="submit">Aplicar</button>
                </form>
            </div>

            <div class="grid-container">
                <div class="grid-header registro">Registro</div>
                <div class="grid-header id_conta">id_conta</div>
                <div class="grid-header id_curso">id_curso</div>
                <div class="grid-header acoes">Permissão</div>

                <div class="grid-item item-registro">1</div>
                <div class="grid-item item-id_conta">24</div>
                <div class="grid-item item-id_curso">31</div>
                <div class="grid-item">
                    <button class="action view"><img src="img/olho.png" alt=""></button>
                </div>

                <div class="grid-item item-registro">2</div>
                <div class="grid-item item-id_conta">12</div>
                <div class="grid-item item-id_curso">23</div>
                <div class="grid-item">
                    <button class="action view"><img src="img/olho.png" alt=""></button>
                </div>  


                <div id="popupID" style="display: none;">
                    <div class="popup">
                        <div class="popup-header">
                            <h2 class="titulo">Informações da vaga</h2>
                            <button class="close-button">✖</button>
                        </div>
                        <form action="#" class="vaga-form">
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="uuid" class="label">UUID:</label>
                                    <input type="text" id="uuid" name="uuid" class="input" />
                                </div>
                                <div class="form-group">
                                    <label for="id_tipo" class="label">Id_status:</label>
                                    <input type="text" id="id_tipo" name="id_tipo" class="input" />
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="nome" class="label">Nome:</label>
                                    <input type="text" id="nome" name="nome" class="input" />
                                </div>
                                <div class="form-group">
                                    <label for="id_empresa" class="label">id_conta:</label>
                                    <input type="text" id="id_empresa" name="id_empresa" class="input" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="descricao" class="label">Descrição:</label>
                                <textarea id="descricao" name="descricao" class="input" maxlength="500"></textarea>
                                <span class="char-count">0/500</span>
                            </div>
                            <div class="form-buttons">
                                <button type="submit" class="b bt-confirmar"><img src="img/correto.png" alt=""></button>
                                <button type="button" class="b bt-cancelar"><img src="img/lixo.png" alt=""></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <script src="js/permissaoCurso.js"></script>
</body>
</html>

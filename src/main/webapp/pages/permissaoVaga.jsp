<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crud Permissao vaga</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <aside class="sidebar">
        <div class="titulo">
            <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
        </div>
        <nav>
            <ul>
                <li><a href="#"><img src="casa.png">Home</a></li>
                <li><a href="../pages/adm.jsp">Adm</a></li>
                <div class="elemento"></div>
                <li class="selecionado"><a href="../PermissaoVaga/permissaoVaga.html">Permissão Vaga</a></li>
                <li><a href="../pages/permissaoCurso.jsp">Permissão Curso</a></li>
                <li><a href="../CrudSetor/Setor.jsp">Setores</a></li>
                <li><a href="tipoArquivo.jsp">Tipo de arquivo</a></li>
                <li><a href="tipoVaga.jsp">Tipo de vaga</a></li>
                <li><a href="situaçãoTrabalhista.jsp">Situação trabalhista</a></li>
                <li><a href="statusCurso.jsp">Status curso</a></li>
            </ul>
        </nav>
        <button class="logout" onclick="window.location.href='../Portal Administrador/portalAdm.html'">Sair</button>
    </aside>


    <div class="container">
        <main class="main-content">
            <div class="titulo">
                <h1>Permissao vaga</h1>
                <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
            </div>

            <!-- Barra de Filtro -->
            <div id="filter-bar" style="display: none;">
                <form action="#" method="GET">
                    <select name="filter-field">
                        <option value="">Escolher campo</option>
                        <option value="todos">Todos</option>
                        <option v
                        alue="registro-filtro">Registro</option>
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
                                    <label for="id_tipo" class="label">Id_tipo:</label>
                                    <input type="text" id="id_tipo" name="id_tipo" class="input" />
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="nome" class="label">Nome:</label>
                                    <input type="text" id="nome" name="nome" class="input" />
                                </div>
                                <div class="form-group">
                                    <label for="id_empresa" class="label">Id_empresa:</label>
                                    <input type="text" id="id_empresa" name="id_empresa" class="input" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="descricao" class="label">Descrição:</label>
                                <textarea id="descricao" name="descricao" class="input" maxlength="500"></textarea>
                                <span class="char-count">0/500</span>
                            </div>
                            <div class="form-buttons">
                                <button type="submit" class="b bt-confirmar"><img src="correto.png" alt=""></button>
                                <button type="button" class="b bt-cancelar"><img src="lixo.png" alt=""></button>
                            </div>
                        </form>
                    </div>
                </div>
                </div>
        </main>
    </div>
                
        <script src="script.js"></script>
    </body>
</html>

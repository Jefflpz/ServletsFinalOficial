<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Adm</title>
    <link rel="stylesheet" href="css/adm.css">
</head>
<body>

    <aside class="sidebar">
        <div class="titulo">
            <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
        </div>
        <nav>
            <ul>
                <li><a href="#"><img src="CrudAdm/casa.png" alt="Home">Home</a></li>
                <div class="elemento"></div>
                <li class="selecionado"><a href="../CrudAdm/adm.html">Adm</a></li>
                <li><a href="listarPermissao_Vaga">Permissão Vaga</a></li>
                <li><a href="listarPermissao_Curso">Permissão Curso</a></li>
                <li><a href="listarSetor">Setores</a></li>
                <li><a href="listarTipo_arquivo">Tipo de arquivo</a></li>
                <li><a href="listarTipo_Vaga">Tipo de vaga</a></li>
                <li><a href="listarSituacao_Trabalhistas">Situação trabalhista</a></li>
                <li><a href="listarStatus_Curso">Status curso</a></li>
            </ul>
        </nav>
        <button class="logout" onclick="window.location.href='../Portal Administrador/portalAdm.html'">Sair</button>
    </aside>

    <div class="container">
        <main class="main-content">
            <div class="titulo">
                <h1>Adm</h1>
                <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
                <button class="inserir-adm">Inserir Adm</button>
            </div>

            <!-- Barra de Filtro -->
            <div id="filtrar-bar" style="display: none;">
                <form action="#">
                    <select name="filter-field">
                        <option value="">Escolher campo</option>
                        <option value="todos">Todos</option>
                        <option value="registro-filtro">Registro</option>
                        <option value="username-filtro">Username</option>
                    </select>
                    <input type="text" name="search" placeholder="Pesquisar...">
                    <button type="submit">Aplicar</button>
                </form>
            </div>

            <div class="grid-container">
                <div class="grid-header registro">Registro</div>
                <div class="grid-header username">Username</div>
                <div class="grid-header senha">Senha</div>
                <div class="grid-header acoes">Ações</div>

                <!-- Exemplo de Itens da Grid -->
                <div class="grid-item item-registro">1</div>
                <div class="grid-item item-username">admin1</div>
                <div class="grid-item">*****</div>
                <div class="grid-item">
                    <button class="action view"><img src="olho.png" alt=""></button>
                    <button class="action edit"><img src="lapis.png" alt=""></button>
                    <button class="action delete"><img src="lixo.png" alt=""></button>
                </div>
                <!-- Repetir para outros admins... -->
            </div>
        </main>

        <!-- Popup para Cadastro -->
        <div id="popupID" style="display: none;">
            <div class="popup">
                <form action="#" class="registrar">
                    <div class="bloco-titulo">
                        <h2 class="titulo">Cadastrar administrador</h2>
                    </div>
                    <div class="form-group">
                        <label for="cadastrar" class="label">Login:</label>
                        <input type="text" id="cadastrar" name="login" placeholder="avnadmin" class="input" required />
                    </div>
                    <div class="form-group">
                        <label  class="label">Digite a senha:</label>
                        <div class="password-container">
                            <input type="password" placeholder="***********" class="input password" required />
                        </div>
                    </div>
                    <button type="submit" class="b bt-registrar">Cadastrar</button>
                    <button type="button" class="b bt-cancelar">Cancelar</button>
                </form>
            </div>
        </div>

        <!-- Popup para Edição -->
        <div id="popupIDadm" style="display: none;">
            <div class="popup">
                <form action="#" class="registrar">
                    <div class="bloco-titulo">
                        <h2 class="titulo">Editar Adm</h2>
                    </div>
                    <div class="form-group">
                        <label for="editar" class="label">Login:</label>
                        <input type="text" id="editar" name="login" placeholder="avnadmin" class="input" required />
                    </div>
                    <div class="form-group">
                        <label class="label">Digite a senha:</label>
                        <div class="password-container">
                            <input type="password" placeholder="***********" class="input password" required />
                        </div>
                    </div>
                    <button type="submit" class="b bt-registrar">Confirmar alterações</button>
                    <button type="button" class="b bt-cancelar-edit">Cancelar</button>
                </form>
            </div>
        </div>

    </div> <!-- Fecha a div.container -->

    <script src="js/adm.js"></script>
</body>
</html>

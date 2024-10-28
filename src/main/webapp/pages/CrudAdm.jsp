<%@ page import="org.example.crud_site.dao.AdmDAO" %>
<%@ page import="org.example.crud_site.model.Adm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/CrudAdm.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Adm</title>
</head>
<body>
<aside class="sidebar">
    <div class="titulo">
        <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
    </div>
    <nav>
        <ul>
            <li><a href="#"><img src="../img/house.png">Home</a></li>
            <li class="selecionado"><a href="#">Adm</a></li>
            <li><a href="listarPermissao_Vaga">Permissão Vaga</a></li>
            <li><a href="listarPermissao_Curso">Permissão Curso</a></li>
            <li><a href="../../listarSetor">Setores</a></li>
            <li><a href="../listarTipo_arquivo">Tipo de arquivo</a></li>
            <li><a href="../../listarTipo_Vaga">Tipo de vaga</a></li>
            <li><a href="../../listarSituacao_Trabalhistas">Situação trabalhista</a></li>
            <li><a href="../../listarStatus_Curso">Status curso</a></li>
        </ul>
    </nav>
    <button href="index.jsp" class="logout">Sair</button>
</aside>

<div class="container">
    <main class="main-content">
        <div class="titulo">
            <h1>Crud Adm</h1>
            <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
            <button class="inserir-adm">Inserir Adm</button>
        </div>

        <!-- Barra de Filtro -->
        <div id="filtrar-bar" style="display: none;">
            <form action="#">
                <select name="filter-field">
                    <option value="">Escolher campo</option>
                    <option value="Todos">Todos</option>
                    <option value="Registro-filtro">Registro</option>
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

            <%
                List<Adm> lista = (List<Adm>) request.getAttribute("listarAdm");

                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
            %>
            <div class="grid-item registro"><%= i+1 %></div>
            <div class="grid-item username"><%= lista.get(i).getUsername() %></div>
            <div class="grid-item"> <%= "*".repeat(lista.get(i).getSenha().length()) %></div>
            <div class="grid-item">
                <button class="action view"><img src="../img/olho.png" alt=""></button>
                <button class="action edit"><img src="../img/lapis.png" alt=""></button>
                <button class="action delete"><img src="../img/lixo.png" alt=""></button>
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

    <div id="popupID" style="display: none;">
        <div class="popup">
            <form action="#" class="registrar">
                <div class="bloco-titulo">
                    <h2 class="titulo">Cadastrar administrador</h2>
                </div>
                <div class="form-group">
                    <label for="login" class="label">Login:</label>
                    <input type="text" id="novoAdm" name="login" placeholder="avnadmin" class="input" required />
                </div>

                <!--                    -->
                <div class="form-group">
                    <!--                      tirei o for="password"-->
                    <label  class="label">Digite a senha:</label>
                    <div class="password-container">
                        <input type="password" placeholder="***********" class="input password"/>
                    </div>
                </div>


                <button type="submit" class="b bt-registrar">Cadastrar</button>
                <button type="button" class="b bt-cancelar">Cancelar</button>
            </form>
        </div>
    </div>


    <div id="popupIDadm" style="display: none;">
        <div class="popup">
            <form action="#" class="registrar">
                <div class="bloco-titulo">
                    <h2 class="titulo">Editar Adm</h2>
                </div>
                <div class="form-group">
                    <label for="login" class="label">Login:</label>
                    <input type="text" id="login" name="login" placeholder="avnadmin" class="input" required />
                </div>

                <div class="form-group">
                    <!--                      tirei o for="password"-->
                    <label class="label">Digite a senha:</label>
                    <div class="password-container">
                        <input type="password" placeholder="***********" class="input password"/>
                    </div>
                </div>


                <button type="submit" class="b bt-registrar">Confirmar alterações</button>
                <button type="button" class="b bt-cancelar-edit">Cancelar</button>
            </form>
        </div>
    </div>
</div>
<script src="../js/adm.js"></script>
</body>
</html>

<%@ page import="org.example.crud_site.dao.AdmDAO" %>
<%@ page import="org.example.crud_site.model.Adm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/adm.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adm</title>
</head>
<body>
<aside class="sidebar">
    <div class="titulo">
        <h1><span class="inclus">Inclus</span><span class="es">es</span></h1>
    </div>
    <nav>
        <ul>
            <li><a href="#"><img src="img/house.png">Home</a></li>
            <div class="elemento"></div>
            <li class="selecionado"><a href="#">Adm</a></li>
            <li><a href="listarPermissaoVaga">Permissão Vaga</a></li>
            <li><a href="listarPermissaoCurso">Permissão Curso</a></li>
            <li><a href="listarSetor">Setores</a></li>
            <li><a href="listarTipoArquivo">Tipo de arquivo</a></li>
            <li><a href="listarTipoVaga">Tipo de vaga</a></li>
            <li><a href="listarSituacaoTrabalhista">Situação trabalhista</a></li>
            <li><a href="listarStatusCurso">Status curso</a></li>
            <li><a href="index.html" class="logout">Sair</a></li>
        </ul>
    </nav>

</aside>

<div class="container">
    <main class="main-content">
        <div class="titulo">
            <h1>Adm</h1>
            <button class="filtrar">Filtrar <span class="pesquisa"> </span></button>
            <button class="inserir-adm">Inserir Adm</button>
        </div>

        <div id="filtrar-bar" style="display: none;">
            <form action="#" method="get">
                <select name="filter-field">
                    <option value="">Escolher campo</option>
                    <option value="todos">Todos</option>
                    <option value="registro-filtro">Registro</option>
                    <option value="username-filtro">Nome</option>
                </select>
                <input type="text" name="search" placeholder="Pesquisar...">
                <button type="submit">Aplicar</button>
            </form>
        </div>

        <div class="grid-container">
            <div class="grid-header registro">Registro</div>
            <div class="grid-header username">Nome</div>
            <div class="grid-header senha">Senha</div>
            <div class="grid-header acoes">Ações</div>

            <%
                List<Adm> lista = (List<Adm>) request.getAttribute("listarAdm");

                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
            %>
            <div class="grid-item registro"><%= i+1 %></div>
            <div class="grid-item username"><%= lista.get(i).getUsername() %></div>
            <div class="grid-item"> <%= (lista.get(i).getSenha() != null) ? "*".repeat(lista.get(i).getSenha().length()) : "" %></div>
            <div class="grid-item">
                <button class="action edit"
                        data-id="<%= lista.get(i).getId() %>"
                        data-username="<%= lista.get(i).getUsername() %>"
                        data-senha="<%= lista.get(i).getSenha() %>">
                    <img src="img/lapis.png" alt="editar adm"></button>
                <form action="excluirADM" method="get">
                    <input type="hidden" name="id" value="<%=lista.get(i).getId()%>">
                    <button id="delete" class="action delete"><img src="img/lixo.png" alt="excluir tipo arquivo"></button>
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

    <div id="popupID" style="display: none;">
        <div class="popup">
            <form id="cadastroForm" action="inserirAdm" method="post" class="registrar">
                <div class="bloco-titulo">
                    <h2 class="titulo">Cadastrar administrador</h2>
                </div>
                <div class="form-group">
                    <label for="novoAdm" class="label">Login:</label>
                    <input type="text" id="novoAdm" name="username" placeholder="avnadmin" class="input" required />
                </div>

                <div class="form-group">
                    <label class="label">Digite a senha:</label>
                    <div class="password-container">
                        <input type="password" placeholder="*" id="novoSenha" name="senha" class="input password" required/>
                    </div>
                </div>
                <span id="erro" class="mensagem-erro"></span>

                <button type="submit" class="b bt-registrar">Cadastrar</button>
                <button type="button" class="b bt-cancelar">Cancelar</button>
            </form>
        </div>
    </div>

    <div id="popupIDadm" style="display: none;">
        <div class="popup">
            <form action="#" class="registrar" id="editarForm">
                <div class="bloco-titulo">
                    <h2 class="titulo">Editar Adm</h2>
                </div>
                <div class="form-group">
                    <label for="admEditado" class="label">Login:</label>
                    <input type="text" id="admEditado" name="login" class="input" required />
                </div>

                <div class="form-group">
                    <label class="label">Digite a senha:</label>
                    <div class="password-container">
                        <input type="password" id="senhaEditada" class="input password" required/>
                    </div>
                </div>
                <span id="erroEditar" class="mensagem-erro"></span>

                <button type="submit" class="b bt-registrar">Confirmar alterações</button>
                <button type="button" class="b bt-cancelar-edit">Cancelar</button>
            </form>
        </div>
    </div>
</div>
<script src="js/adm.js"></script>
</body>
</html>

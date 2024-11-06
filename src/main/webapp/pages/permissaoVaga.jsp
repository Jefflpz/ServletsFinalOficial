<%@ page import="org.example.crud_site.model.PermissaoVaga" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.crud_site.dao.PermissaoVagaDAO" %>
<%@ page import="org.example.crud_site.model.Vaga" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crud Permissao vaga</title>
    <link rel="stylesheet" href="css/permissaoVaga.css">
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
                <div class="elemento"></div>
                <li class="selecionado"><a href="#">Permissão Vaga</a></li>
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
                <h1>Permissao vaga</h1>
                <button class="filtrar">Filtrar <span class="pesquisa"></span></button>
            </div>

            <!-- Barra de Filtro -->
            <div id="filter-bar" style="display: none;">
                <form action="#" method="GET">
                    <select name="filter-field">
                        <option value="">Escolher campo</option>
                        <option value="todos">Todos</option>
                        <option value="registro-filtro">Registro</option>
                        <option value="data-filtro">Data de Solicitação</option>
                        <option value="permissao-filtro">Permissão</option>
                    </select>

                    <input type="text" name="search" placeholder="Pesquisar...">

                    <button type="submit">Aplicar</button>
                </form>
            </div>

            <div class="grid-container">
                <div class="grid-header registro">Registro</div>
                <div class="grid-header id_conta">Data de solicitação</div>
                <div class="grid-header id_conta">Permissão</div>
                <div class="grid-header acoes">Ações</div>

                <%
                    List<PermissaoVaga> lista = (List<PermissaoVaga>) request.getAttribute("listaPermissao_Vaga");

                    if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        PermissaoVagaDAO permissaoVagaDAO = new PermissaoVagaDAO();
                %>
                <div class="grid-item registro"><%= i+1 %></div>
                <div class="grid-item"><%= lista.get(i).getDtSolicitacao() %></div>
                <div class="grid-item"><%= lista.get(i).getPermissao() %></div>
                <div class="grid-item">
                    <form action="buscarPermissao_Vaga" method="post">
                        <%Vaga vaga = permissaoVagaDAO.buscarPermissaoVagaPorId(lista.get(i).getIdVaga());%>
                        <input type="hidden" name="id" value="<%=vaga.getId()%>">
                        <button class="action view"
                                data-id="<%= vaga.getId() %>"
                                data-tipo="<%= vaga.getId_tipo() %>"
                                data-nome="<%= vaga.getNome() %>"
                                data-idEmpresa="<%= vaga.getIdEmpresa() %>"
                                data-descricao="<%= vaga.getDescricao() %>">
                            <img src="img/olho.png" alt=""></button>
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


                <div id="popupID" style="display: none;">
                    <div class="popup">
                        <div class="popup-header">
                            <h2 class="titulo">Informações da vaga</h2>
                            <button class="close-button">✖</button>
                        </div>
                        <form action="buscarPermissao_Vaga" class="vaga-form">
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
                                <button type="submit" class="b bt-confirmar"><img src="img/correto.png" alt=""></button>
                                <button type="button" class="b bt-cancelar"><img src="img/lixo.png" alt=""></button>
                            </div>
                        </form>
                    </div>
                </div>
                </div>
        </main>
    </div>
                
        <script src="js/permissaoVaga.js"></script>
    </body>
</html>

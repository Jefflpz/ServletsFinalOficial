<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>CRUD status curso</title>
  <link rel="stylesheet" href="css/statusCurso.css">
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
            <li><a href="listarPermissao_Curso">Permissão Curso</a></li>
            <li><a href="listarPermissao_Vaga">Permissão Vaga</a></li>
            <li><a href="listarSetor">Setores</a></li>
            <li><a href="listarSituacao_Trabalhistas">Situação trabalhista</a></li>
            <li class="selecionado"><a href="#">Status curso</a></li>
            <li><a href="listarTipo_arquivo">Tipo de arquivo</a></li>
            <li><a href="listarTipo_Vaga">Tipo de vaga</a></li>
            <li><a href="index.html" class="logout">Sair</a></li>
        </ul>
    </nav>
  </aside>


    <div class="container">
        <main class="main-content">
            <div class="titulo">
                <h1>Status curso</h1>
                <button class="filtrar">Filtrar <span class="pesquisa"> > </span></button>
                <button class="inserir-adm">Inserir Status </button>
            </div>

            <!-- Barra de Filtro -->
            <div id="filtrar-bar" style="display: none;">
                <form action="#">
                    <select name="filter-field">
                        <option value="">Escolher campo</option>
                        <option value="todos">Todos</option>
                        <option value="registro-filtro">Registro</option>
                        <option value="uuid-filtro">UUID</option>
                        <option value="status-filtro">Status</option>
                    </select>

                    <input type="text" name="search" placeholder="Pesquisar...">

                    <button type="submit">Aplicar</button>
                </form>
            </div>

            <div class="grid-container">
                <div class="grid-header registro">Registro</div>
                <div class="grid-header username">UUID</div>
                <div class="grid-header Nome">Status</div>
                <div class="grid-header ações">Ações</div>


                <div class="grid-item item-registro">1</div>
                <div class="grid-item item-username">admin1</div>
                <div class="grid-item">Em andamento</div>
                <div class="grid-item">
                    <button class="action view"><img src="img/olho.png" alt=""></button>
                    <button class="action edit"><img src="img/lapis.png" alt=""></button>
                    <button class="action delete"><img src="img/lixo.png" alt=""></button>
                </div>  

                <div class="grid-item item-registro">2</div>
                <div class="grid-item item-username">admin2</div>
                <div class="grid-item">Concluido</div>
                <div class="grid-item">
                    <button class="action view"><img src="img/olho.png" alt=""></button>
                    <button class="action edit"><img src="img/lapis.png" alt=""></button>
                    <button class="action delete"><img src="img/lixo.png" alt=""></button>
                </div>  

                <div class="grid-item item-registro">3</div>
                <div class="grid-item item-username">admin3</div>
                <div class="grid-item">Concluido</div>
                <div class="grid-item">
                    <button class="action view"><img src="img/olho.png" alt=""></button>
                    <button class="action edit"><img src="img/lapis.png" alt=""></button>
                    <button class="action delete"><img src="img/lixo.png" alt=""></button>
                </div>  

                <div class="grid-item item-registro">4</div>
                <div class="grid-item item-username">admin4</div>
                <div class="grid-item">Cancelado</div>
                <div class="grid-item">
                    <button class="action view"><img src="img/olho.png" alt=""></button>
                    <button class="action edit"><img src="img/lapis.png" alt=""></button>
                    <button class="action delete"><img src="img/lixo.png" alt=""></button>
                </div>  

                <div class="grid-item item-registro">5</div>
                <div class="grid-item item-username">admin5</div>
                <div class="grid-item">Em andamento </div>
                <div class="grid-item">
                    <button class="action view"><img src="img/olho.png" alt=""></button>
                    <button class="action edit"><img src="img/lapis.png" alt=""></button>
                    <button class="action delete"><img src="img/lixo.png" alt=""></button>
                </div>          
            </div>

        </main>
        
        <div id="popupID" style="display: none;">
            <div class="popup">
              <form action="#" class="registrar">
                <div class="bloco-titulo">
                    <h2 class="titulo">Inserir status</h2>
                </div>
                <div class="form-group">
                  <label for="login" class="label">UUIDgin:</label>
                  <input type="text" id="login" name="login" placeholder="avnadmin" class="input" required />
                </div>
          
                <div class="form-group">
                  <label for="password" class="label">Nome:</label>
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
                    <h2 class="titulo">Editar Status</h2>
                </div>
                <div class="form-group">
                  <label for="login" class="label">UUID:</label>
                  <input type="text" id="login" name="login" placeholder="avnadmin" class="input" required />
                </div>
          
                <div class="form-group">
                  <label for="password" class="label">Nome:</label>
                  <div class="password-container">
                    <input type="password" placeholder="***********" class="input password"/>
                  </div>
                </div>
          

                  <button type="submit" class="b bt-registrar">Confirmar alterações</button>
                  <button type="button" class="b bt-cancelar-edit">Cancelar</button>
              </form>
            </div>
          </div>


  <script src="js/statusCurso.js"></script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/intermediaria.css">
    <title>Incluses - Escolha sua Área</title>
</head>
<body>
    <div class="container">
        <div class="titulos">
            <h1 class="titulo">Inclus<span class="titulo-destaque">es</span></h1>
            <p class="subtitulo">Qual área quer seguir?</p>
        </div>
        <hr>
        <div class="opcoes">
            <button class="botao" onclick="redirecionarParaServlet('listarAdm')">
                <div class="titulo_cards">
                    <span>Cruds</span>
                    <img src="../img/officedatabase_104402 1.png" alt="Ícone Gráfico">
                </div>
                <p>Analisar em tempo real registrados no banco de dados relacional do Incluses</p>
            </button>
            <button class="botao" onclick="redirecionarParaServlet('listarGrafico')">
                <div class="titulo_cards">
                    <span>Gráficos</span>
                    <img src="../img/Group 213.png" alt="Ícone Gráfico">
                </div>
                <p>Analisar os dados das pesquisas feitas, por meio de gráficos com interações, feitas no Power BI</p>
            </button>
        </div>
    </div>
</body>
<script>
    function redirecionarParaServlet(servlet) {
        window.location.href = 'http://localhost:8080/CRUD_Site_war_exploded/' + servlet;
    }
</script>
</html>

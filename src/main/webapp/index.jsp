<%--
  Created by IntelliJ IDEA.
  User: jeffersonlopes-ieg
  Date: 22/10/2024
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/LandingPage.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Palanquin:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
  <title>Login Administrador</title>
</head>
<body>
<button class="voltar">🢐 Voltar</button>
<div class="container">
  <div class="incluses">
    <h1 id="inclus">Inclus<span id="es">es</span></h1>
  </div>
  <hr>
  <h2>Login administrador</h2>
  <form id="formLogin">
    <label for="usuario">Usuário:</label>
    <input type="text" id="usuario" placeholder="Ex: admAlgumaCoisa" required>
    <span id="erroAdm" class="mensagem-erro"></span>
    <label for="senha">Digite a senha:</label>
    <div class="senha-container">
      <input type="password" id="senha" placeholder="Senha" required>
      <span class="mostrar-senha"><img src="img/Icone_olhoA.png" class="imagem-olho" alt="mostrar senha"></span>
    </div>
    <span id="erroSenha" class="mensagem-erro"></span>
    <button type="submit">Entrar</button>
  </form>
</div>
<script src="js/LoginAdm.js"></script>
</body>

</html>

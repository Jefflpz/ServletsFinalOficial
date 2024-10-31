package org.example.crud_site.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSenha {

    // Método para gerar o hash da senha
    public String hashSenha(String senha) throws NoSuchAlgorithmException {
        // Objeto para criar o hash da senha
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Converte o hash para string hexadecimal
        byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

        // Constrói uma string hexadecimal a partir do hash
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02X", b));
        }

        // Retorna a string hexadecimal
        return hexString.toString();
    }
}

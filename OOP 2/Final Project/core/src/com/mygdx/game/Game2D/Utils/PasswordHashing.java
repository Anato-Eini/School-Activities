package com.mygdx.game.Game2D.Utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHashing {
    public static String hashPassword(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(password.getBytes());
        byte[] hashedPassword = md.digest();
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}

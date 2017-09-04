package br.com.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAuthToken {
	
    public static String GerarToken(String login) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        String token = login; // deve ficar (login + datetime) ex: joao28/04/2017-13:19

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(token.getBytes("UTF-8"));
        
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }        
        
        return hexString.toString();
        
    }

}

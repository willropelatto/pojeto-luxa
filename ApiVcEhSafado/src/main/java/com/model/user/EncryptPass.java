package com.model.user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPass {

	public byte[] stringToMD5(String value) {
        byte[] hash = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            InputStream stream = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
            DigestInputStream inputStream = new DigestInputStream(stream, md5);
            while (inputStream.read() != -1);
            hash = md5.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return hash;
    }
}

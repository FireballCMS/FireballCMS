package com.ncc490.cmpt405.models.admin;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;

public final class LoginService {

    private static final String ALGO = "AES";
    private static final byte[] salt = new byte[]{ 'T', 'h', 'e', 'B', 'e', 's', 't',
            'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public String createAccount(String email, String password) {
        if (!email.matches(EMAIL_PATTERN)) {
            return "Error: invalid email";
        }

        try {
            return encrypt(email + password);
        } catch (Exception e) {
            return "404: Server Error";
        }
    }

    private String encrypt(String Data) throws Exception {
        Key key = new SecretKeySpec(salt, ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    private String decrypt(String encryptedData) throws Exception {
        Key key = new SecretKeySpec(salt, ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
}

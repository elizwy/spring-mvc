package com.zwy.springmvc.config;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    /*
     * DES 加密
     */
    public static String encrypt(String data, String key) throws Exception {

            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");
            Cipher cipher = null;
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherBytes = cipher.doFinal(data.getBytes());

        return byte2HexStr(cipherBytes);
    }

    private static String byte2HexStr(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
        }
        return sb.toString().trim();
    }


    /*
     * DES 解密
     */
    public static String decrypt(String data,String key)  throws Exception{
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data.getBytes());

        return byte2HexStr(plainBytes);
    }

}

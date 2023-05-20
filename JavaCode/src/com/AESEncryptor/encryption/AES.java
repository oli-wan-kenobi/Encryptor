package com.AESEncryptor.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {

    private String[][] encryptedTextArray;

    //Big constructor such wow. But that's all we need.
    public AES(String[][] textArray, boolean[] hitArray) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException  {

        int x=textArray.length;
        int y=textArray[0].length;
        encryptedTextArray = new String[x][y];
        SecretKey key = AES.generateKey(128);
        IvParameterSpec ivParameterSpec = AES.generateIv();

        for(int i=0;i<x;i++) {
            for (int j = 0; j < y; j++) {
                if(hitArray[j] && 0 != i)
                    this.encryptedTextArray[i][j] = AES.encrypt("AES/CBC/PKCS5Padding", textArray[i][j], key, ivParameterSpec);
                else
                    this.encryptedTextArray[i][j]=textArray[i][j];
            }
        }
        System.out.println("\n\nThis is the unencrypted (input) array!");
        for(int i=0;i<x;i++) {
            System.out.print("\n");
            for (int j = 0; j < y; j++) {
                System.out.print(textArray[i][j] + "\t");
            }
        }

        System.out.println("\n\nThis is the encrypted (output) array!");
        for(int i=0;i<x;i++) {
            System.out.print("\n");
            for (int j = 0; j < y; j++) {
                System.out.print(encryptedTextArray[i][j] + "\t");
            }
        }
    }

    //Generate some keys
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }


    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return new String(Base64.getEncoder().encodeToString(cipherText));
    }

    //I can call this to get my array, so the file output class can do its job.
    public String[][] getEncryptedTextArray() {
        return encryptedTextArray;
    }
}

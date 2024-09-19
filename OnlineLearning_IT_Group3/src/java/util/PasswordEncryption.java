/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author DTC
 */
public class PasswordEncryption {
     public static String password_encryption(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public static boolean verify(String inputPassword, String hashPassword) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(inputPassword.getBytes());
//            byte[] digest = md.digest();
//            String myChecksum = DatatypeConverter
//                    .printHexBinary(digest).toUpperCase();
//            System.out.println(myChecksum);
//            return hashPassWord.equals(myChecksum);
//        } catch (NoSuchAlgorithmException ex) {
//        }
//        return false;
        try {
            return inputPassword.equals(hashPassword);
        } catch (Exception e) {
        }
        return false;
    }

}

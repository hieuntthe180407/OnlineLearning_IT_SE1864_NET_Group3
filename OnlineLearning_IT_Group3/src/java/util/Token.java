/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author DTC
 */
public class Token {
    private static final String SECRET_KEY = "vinh"; // Thay thế bằng secret key của bạn
    private static final long TOKEN_VALIDITY_DURATION_MS = 1 * 60 * 1000; // 30 phút
    // Dùng để tạo một token mới
    public static String generateToken() {
        long timestamp = new Date().getTime();
        long expirationTime = timestamp + TOKEN_VALIDITY_DURATION_MS; // Thời gian hết hạn
        String dataToEncode = SECRET_KEY + timestamp + expirationTime;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(dataToEncode.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hashBytes) + "." + expirationTime;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    /* Dùng để kiểm tra tính hợp lệ của 1 token 
      - Trả về true nếu token hợp lệ và về false nếu không hợp lệ
    */
    public static boolean isTokenValid(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 2) {
                return false; // Token không hợp lệ
            }

            String encodedToken = parts[0];
            long expirationTime = Long.parseLong(parts[1]);
            long currentTime = new Date().getTime();

            // Kiểm tra thời gian hết hạn của token
            if (currentTime < expirationTime) {
                // Thời gian hết hạn trong tương lai và trong khoảng thời gian cho phép
                return true; // Token hợp lệ
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Token không hợp lệ
    }
    public static void main(String[] args) {
        String token = generateToken();
        System.out.println("Generated Token: " + token);

        boolean isValid = isTokenValid("RIg0sAhfdcy1VZbUh4okMWsgROG06vIzXdi0qE05lic=.1726468665854");
        System.out.println("Token is valid: " + isValid);
    }
    
    
}

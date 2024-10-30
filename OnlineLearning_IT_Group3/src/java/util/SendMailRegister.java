package util;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Date;

import java.util.logging.Level;

import java.util.logging.Logger;

public class SendMailRegister {

    // Địa chỉ email người gửi
    static final String from = "keeplearnedunow@gmail.com";
    // Mật khẩu ứng dụng của email người gửi (sử dụng mật khẩu ứng dụng khi bật 2FA)
    static final String password = "lkhi egjt gwxo pqta";

    // Phương thức gửi email xác thực
    public static boolean sendMailRegister(String to, String verificationCode) {
        // Cấu hình các thuộc tính SMTP
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");// Tin cậy máy chủ Gmail
        props.put("mail.smtp.host", "smtp.gmail.com"); // Máy chủ SMTP của Gmail
        props.put("mail.smtp.port", "587"); // Cổng TLS
        props.put("mail.smtp.auth", "true");// Kích hoạt xác thực
        props.put("mail.smtp.starttls.enable", "true");  // Kích hoạt STARTTLS

        // Tạo Authenticator cho xác thực mật khẩu
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Tạo một phiên làm việc với thông tin xác thực
        Session session = Session.getInstance(props, auth);

        try {
            // Tạo một email mới
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // Đặt địa chỉ email người gửi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false)); // Đặt người nhận
            message.setSubject("Email Verification");// Tiêu đề email

            // Nội dung HTML của email
            String htmlContent = "<!doctype html>\n"
                    + "<html lang=\"en-US\">\n"
                    + "<head>\n"
                    + "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n"
                    + "    <title>Email Verification</title>\n"
                    + "    <style type=\"text/css\">\n"
                    + "        a:hover {text-decoration: underline !important;}\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    <h1>Email Verification</h1>\n"
                    + "    <p>Your verification code is: <strong>" + verificationCode + "</strong></p>\n"
                    + "    <p>Please enter this code to verify your email address.</p>\n"
                    + "</body>\n"
                    + "</html>";

            message.setContent(htmlContent, "text/HTML; charset=UTF-8"); // Đặt nội dung email dưới dạng HTML
            message.setSentDate(new Date());  // Đặt thời gian gửi
            message.setReplyTo(InternetAddress.parse(from, false)); // Đặt địa chỉ trả lời

            // Gửi email
            Transport.send(message);
            System.out.println("Email sent successfully!"); 
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(SendMailRegister.class.getName()).log(Level.SEVERE, "Email sending failed", ex);
            return false;
        }
    }

}

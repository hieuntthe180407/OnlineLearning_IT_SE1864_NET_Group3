/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author ADMIN
 */
import java.util.*;
import java.lang.*;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static util.SendMail.from;
import static util.SendMail.password;

public class SendMailRegister {

    static final String from = "vinhdubai10@gmail.com";
    static final String password = "obpd tqix maif sevz";


    public static boolean sendMailRegister(String to, String verificationCode) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //smtp host
        props.put("mail.smtp.port", "587");   //tls 597, ssl465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //authen
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        };

        //session new
        Session session = Session.getInstance(props, auth);

        //send mail
        //tao tin nhan moi
        MimeMessage message = new MimeMessage(session);

        try {
            //noi dung
            // message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            //nguoi gui
            message.setFrom(from);
            //nguoi nhan
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //tieu de
            message.setSubject("Password Reset");

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
            message.setSentDate(new Date());
            //quy dinh phan hoi 
            message.setReplyTo(null);
            //noi dung chu
            // message.setText("hiii");
            message.setContent(htmlContent, "text/HTML; charset=UTF-8");

            //gui mail
            Transport.send(message);
            System.out.println("Send successfully");
            return true;
        } catch (MessagingException ex) {
            System.out.println("Send failed!");

            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}



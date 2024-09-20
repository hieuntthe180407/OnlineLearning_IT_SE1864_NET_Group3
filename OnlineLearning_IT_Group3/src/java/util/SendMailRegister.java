


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

    static final String from = "keeplearnedunow@gmail.com";
    static final String password = "lkhi egjt gwxo pqta"; // Use app password if 2FA is enabled

    public static boolean sendMailRegister(String to, String verificationCode) {
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
        props.put("mail.smtp.port", "587"); // TLS port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        // Authenticator for password authentication
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Create session with authenticator
        Session session = Session.getInstance(props, auth);

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // Correct format for sender
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject("Email Verification");

            // Create HTML content for the email
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

            message.setContent(htmlContent, "text/HTML; charset=UTF-8");
            message.setSentDate(new Date());
            message.setReplyTo(InternetAddress.parse(from, false)); // Set reply-to address

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully!");
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(SendMailRegister.class.getName()).log(Level.SEVERE, "Email sending failed", ex);
            return false;
        }
    }

}

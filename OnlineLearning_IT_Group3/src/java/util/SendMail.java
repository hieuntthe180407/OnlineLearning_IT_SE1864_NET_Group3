/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

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

/**
 *
 * @author DTC
 */
public class SendMail {

    static final String from = "vinhdubai10@gmail.com";
    static final String password = "obpd tqix maif sevz";

    //Phương thức sendMail: Gửi email chứa mã token đến email của người nhận.
    public static boolean sendMail(String to, String token) {
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
                    + "\n"
                    + "<head>\n"
                    + "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n"
                    + "    <title>Reset Password  </title>\n"
                    + "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n"
                    + "    <style type=\"text/css\">\n"
                    + "        a:hover {text-decoration: underline !important;}\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n"
                    + "    <!--100% body table-->\n"
                    + "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n"
                    + "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n"
                    + "        <tr>\n"
                    + "            <td>\n"
                    + "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n"
                    + "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"height:80px;\">&nbsp;</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"text-align:center;\">\n"
                    + "                          <a href=\"http://localhost:9999/OnlineLearning_IT_Group3/\" title=\"logo\" target=\"_blank\">\n"
                    + "                            <img width=\"200\" src=\"https://scontent.fhan2-3.fna.fbcdn.net/v/t39.30808-6/376273019_1248686809149962_565464020790828756_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=49d041&_nc_ohc=mgmAnWL9sC8AX8-V1vA&_nc_ht=scontent.fhan2-3.fna&oh=00_AfCQ7bsNNkiEAyf1OEXNc60lKdiD5XBOBCU7y7k66_BWWw&oe=6518122D\" title=\"logo\" alt=\"logo\">\n"
                    + "                          </a>\n"
                    + "                        </td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"height:20px;\">&nbsp;</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td>\n"
                    + "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n"
                    + "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n"
                    + "                                <tr>\n"
                    + "                                    <td style=\"height:40px;\">&nbsp;</td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <td style=\"padding:0 35px;\">\n"
                    + "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">Reset Your Password</h1>\n"
                    + "                                        <span\n"
                    + "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n"
                    + "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n"
                    + "                                            We cannot simply send you your old password. A unique link to reset your\n"
                    + "                                            password has been generated for you. To reset your password, click the\n"
                    + "                                            following link and follow the instructions.\n"
                    + "                                        </p>\n"
                    + "                                        <a href=\"http://localhost:9999/OnlineLearning_IT_Group3/resetPassword.jsp?email=" + to + "&token=" + token + "\"\n"
                    + "                                            style=\"background:#138496;text-decoration:none !important;    cursor: pointer; font-weight:600; margin-top:65px; color:#fff;text-transform:uppercase; font-size:14px;padding:20px 70px;display:inline-block;\">Reset \n"
                    + "                                            Password</a>\n"
                    + "                                      <p style=\"color:#da2222; font-size:15px;line-height:24px; margin-top:20px;\">\n"
                    + "                                            *This email is only valid for 1 minutes from the time it is sent.\n"
                    + "                                        </p>\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <td style=\"height:40px;\">&nbsp;</td>\n"
                    + "                                </tr>\n"
                    + "                            </table>\n"
                    + "                        </td>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"height:20px;\">&nbsp;</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"text-align:center;\">\n"
                    + "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>ELearning</strong></p>\n"
                    + "                        </td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"height:80px;\">&nbsp;</td>\n"
                    + "                    </tr>\n"
                    + "                </table>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "    </table>\n"
                    + "    <!--/100% body table-->\n"
                    + "</body>\n"
                    + "\n"
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

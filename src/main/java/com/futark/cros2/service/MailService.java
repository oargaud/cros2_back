package com.futark.cros2.service;


import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.net.URI;
import java.util.Properties;

@Service
public class MailService {

    private String username;
    private String password;

    private final Properties prop;


    public MailService() {
        prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        this.username = "gohorlaaapin";
        this.password = "killian69";

    }

//    public MailService(String host, int port, String username, String password) {
////        prop = new Properties();
////        prop.put("mail.smtp.auth", true);
////        prop.put("mail.smtp.starttls.enable", "true");
////        prop.put("mail.smtp.host", host);
////        prop.put("mail.smtp.port", port);
////        prop.put("mail.smtp.ssl.trust", host);
//
//         prop = System.getProperties();
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "465");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.starttls.required", "true");
//        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//
//
//        this.username = username;
//        this.password = password;
//    }
//
//    public MailService(String host, int port) {
//        prop = new Properties();
//        prop.put("mail.smtp.host", host);
//        prop.put("mail.smtp.port", port);
//    }

//    public static void main(String... args) {
//        try {
////            new MailService("smtp.mailtrap.io", 25, "87ba3d9555fae8", "91cb4379af43ed")
//            new MailService("smtp.gmail.com", 465, "gohorlaaapin", "killian69")
//                    .sendMail("vladinouch@gmail.com","YOUHOOOUUUUUU!!!! IT WORKS","This is my first email using JavaMailer   laaaapin ouaip :D");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void sendMail(String destinataire, String topic, String msg) throws Exception {

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("gohorlaaapin@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
        message.setSubject(topic);


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

//        MimeBodyPart attachmentBodyPart = new MimeBodyPart();

//        attachmentBodyPart.attachFile(getFile());

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
//        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    private File getFile() throws Exception {
        URI uri = this.getClass()
                .getClassLoader()
                .getResource("attachment.txt")
                .toURI();
        return new File(uri);
    }

}
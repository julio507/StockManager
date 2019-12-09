package com.yard.stockmanager.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import com.yard.stockmanager.persistence.entity.Funcionario;
import com.yard.stockmanager.useful.Error;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class Mail {
    public static void sendMessage(Funcionario func, Address[] toUsers, String subject, String text, String annexPath)
    {
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        //propriedades
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.ssl.socketFactory", sf);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(func.getEmail(), func.getSenhaemail());//("seuEmail@gmail.com","senha");
                    }
                });

        /** Ativa Debug para sessão */
        session.setDebug(false);

        try {
            // cria a mensagem
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(func.getEmail()));//Remetente
            Address[] toUser = toUsers;//InternetAddress.parse("emailDestino@gmail.com");//Destinatário(s)
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);

            // cria a primeira parte da mensagem
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(text);

            // cria a segunda parte da mensage
            MimeBodyPart mbp2 = new MimeBodyPart();

            if (annexPath != "") {
                // anexa o arquivo na mensagem
                FileDataSource fds = new FileDataSource(annexPath);
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(fds.getName());
            }

            // cria a Multipart
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);

            // adiciona a Multipart na mensagem
            message.setContent(mp);

            // configura a data: cabecalho
            message.setSentDate(new Date());

            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            Error.message("Mensagem enviada com Sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


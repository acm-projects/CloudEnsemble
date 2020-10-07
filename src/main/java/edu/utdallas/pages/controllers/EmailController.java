package edu.utdallas.pages.controllers;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailController {

    public static final String from = "Ltprichard2014@gmail.com";
    public static final Properties properties = System.getProperties();
    public static final Session session = Session.getDefaultInstance(properties);

    static {
        properties.setProperty("mail.stmp.host", "localhost");
    }

    /**
     * Sends an email to verify an account
     * @param userName userName for account
     * @param to email address to send to
     * @return json identifies success or a failure of sending
     */
    public static String sendVerificationEmail(String userName, String to) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("New Pages Account for " + userName);
            message.setText("This is actual message");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

}

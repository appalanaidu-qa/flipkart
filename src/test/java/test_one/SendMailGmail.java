package test_one;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailGmail {

    public static void main(String[] args) {

        // Sender's Gmail credentials
        final String username = "appalanaidu.qa@gmail.com"; // your Gmail username
        final String password = "Devakivada@0612";        // your Gmail password

        // Setting up Gmail SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get a session instance with the Gmail server
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            // Creating a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("appalanaidu.qa@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("kiran.devakivada@gmail.com")); // Recipient's email
            message.setSubject("Test Email from Gmail");
            message.setText("Hello, this is a test email sent using Gmail SMTP!");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

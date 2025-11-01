package test_one;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail2 {

    public static void main(String[] args) {

        // Sender's Outlook email credentials
        final String username = "appala.naidu@vodafone.com"; // your Outlook email address
        final String password = "";          // your email password (or app-specific password if 2FA is enabled)

        // Setting up Outlook SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        // Get a session instance with the Outlook server
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            // Creating a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("appala.naidu@vodafone.com")); // From email address
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("kukkala.ranjith1@vodafone.com"));     // Recipient's email
            message.setSubject("Test Email from Outlook");
            message.setText("Hello, this is a test email sent using Outlook SMTP!");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

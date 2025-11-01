package test_one;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMailWithAttachment {
    static String subject = "Regression Status"; // Subject of the email
    static String to = "appala.naidu@vodafone.com";     // Receiver's email
    static String cc = "appala.naidu@vodafone.com";   // CC email
    static String attachmentPath = "D:\\New folder\\Framework\\MZ\\src\\Results\\Run_2024_10_08_18_28_11_pm\\RegressionReport.html";  // Path to the attachment
    static String smtpServer = "appsmtp-north.internal.vodafone.com";  // SMTP server
    static String smtpUser = "appala.naidu@vodafone.com";       // SMTP username
    static String smtpPassword = "abcd";   // SMTP password
    static String from = "appala.naidu@vodafone.com"; // Hardcoded "from" email

    public static void main(String[] args) {
        // Hardcoded values
        System.out.println("Subject: " + subject);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Cc: " + cc);
        System.out.println("Attachment: " + attachmentPath);

        // Define the email body directly in the code
        String mailBody = "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "<title>Regression Status</title><style>body { font-family: Arial, sans-serif; } h1 { color: #333; } p { font-size: 14px; }</style></head>"
                + "<body><h1>Regression Status Report</h1>"
                + "<p>Dear Team,</p>"
                + "<p>This email contains the regression status for the latest Run</p>"
                + "<p>Status: <span style=\"color: green;\">Passed</span></p>"
                + "<p>Attached to this email is the detailed report.</p>"
                + "<p>Best regards,<br>Naidu</p></body>"
                + "</html>";

        // Send the email
        try {
            sendEmail(smtpServer, smtpUser, smtpPassword, from, to, cc, subject, mailBody, attachmentPath);
            System.out.println("Mail sent successfully");
        } catch (Exception e) {
            System.out.println("Failed to send mail");
            e.printStackTrace();
        }
    }

    // Method to send an email with an attachment, including SMTP authentication
    static void sendEmail(String smtpServer, final String smtpUser, final String smtpPassword, String from, String to, String cc, String subject, String body, String attachmentPath) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpServer);
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");  // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS for encryption (optional)

        // Create session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPassword);
            }
        });

        // Create a new email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        message.setSubject(subject);

        // Create the message body part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html");

        // Create a multipart message for attachment
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Attachment part
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentPath);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(new File(attachmentPath).getName());
        multipart.addBodyPart(attachmentBodyPart);

        // Set the complete message parts
        message.setContent(multipart);

        // Send the message
        Transport.send(message);
    }
}

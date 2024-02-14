package com.ayiko.backend.util;


import com.ayiko.backend.exception.CommonApplicationException;
import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@Component
public class OtpSender {
    public String sendOtp(String toEmail){
        // Email credentials
        String fromEmail = "your_email@example.com";
        String password = "your_password";

        // Generate a random 6-digit OTP
        int otp = (int) (Math.random() * 900000) + 100000;
        String otpString = Integer.toString(otp);

        // Email configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.example.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            //Sending OTP Email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your OTP");
            message.setText("Your OTP is: " + otpString);

            Transport.send(message);

            System.out.println("OTP sent successfully to " + toEmail);
            return otpString;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new CommonApplicationException("Email address already registered");
        }
    }
}



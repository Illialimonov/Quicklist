package com.ilusha.marketplaceAPI.configS3;

import com.ilusha.marketplaceAPI.service.UserService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SendEmailService {
    private JavaMailSender mailSender;
    private UserService userService;


    public void sendEmail(String recipient, String resetCode){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ilialimits222@gmail.com");
        message.setTo(recipient);
        message.setText("Your code to reset the password is: " + resetCode);
        message.setSubject("Code for resetting your password");

        mailSender.send(message);

        System.out.println("Sent successfully");

    }

    public void contactSeller(String messageContent, String recipient, String listingName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("ilialimits222@gmail.com");
            helper.setTo(recipient);
            helper.setSubject("Regarding: " + listingName);

            // Customize your HTML message with a specific font
            String htmlContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif;'>" +
                    "<h2>Message from: " + userService.getCurrentUser().getEmail() + "</h2>" +  // Include sender's name
                    "<h3>Regarding your listing: " + listingName + "</h3>" +
                    "<p>" + messageContent + "</p>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true); // true indicates it's an HTML message

            mailSender.send(message);
            System.out.println("Sent successfully");
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }

}

package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendNotificaitionAsync(CreateCustomerDTO customer) throws MailException, InterruptedException {
        System.out.println("Async metoda se izvrsava u drugom Threadu u odnosu na prihvaceni zahtev. Thread id: " + Thread.currentThread().getId());
        //Simulacija duze aktivnosti da bi se uocila razlika
        Thread.sleep(5000);
        System.out.println("Slanje emaila...");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setTo(customer.email);
            helper.setFrom(env.getProperty("spring.mail.username"));
            System.out.println(env.getProperty("spring.mail.username"));
            helper.setSubject("Mejl za potvrdu registracije ISA-team-34");

            // HTML content with basic styling
            String htmlContent = "<p>Dear <strong>" + customer.name + "</strong>,</p>\n" +
                    "<p>Thank you for choosing ISA! We're excited to have you on board.</p>\n" +
                    "<p>Your registration is almost complete. Please click the following link to activate your account:</p>\n" +
                    "<p><a href=\"#\">Activation Link</a></p>\n" +
                    "<p>If you have any questions, feel free to contact our support team.</p>\n" +
                    "<p>Best regards,<br/>The ISA Team</p>";

            helper.setText(htmlContent, true);  // Set to true for HTML content
            javaMailSender.send(mimeMessage);

            System.out.println("Email poslat!");
        } catch (MessagingException e) {
            throw new MailException("Failed to send email", e) {
            };
        }

        /*SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(customer.email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        System.out.println(env.getProperty("spring.mail.username"));
        mail.setSubject("Mejl za potvrdu registracije ISA-team-34");

        String htmlContent = "<p>Pozdrav <strong>" + customer.name + "</strong>,</p>"
                + "<p>Hvala što pratiš ISA.</p>"
                + "<p style='color: #007bff;'>Tvoja registracija je uspešna!</p>"
                + "<p>...</p>";

        mail.setText(htmlContent);
        mail.setHtml(true);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");*/
    }
}

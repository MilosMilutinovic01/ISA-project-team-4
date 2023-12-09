package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

            String htmlContent = "<p>Dear <strong>" + customer.name + "</strong>,</p>\n" +
                    "<p>Thank you for choosing ISA! We're excited to have you on board.</p>\n" +
                    "<p>Your registration is almost complete. Please click the following link to activate your account:</p>\n" +
                    "<p><a href=\"http://localhost:4200/\">Activation Link</a></p>\n" +
                    "<p>If you have any questions, feel free to contact our support team.</p>\n" +
                    "<p>Best regards,<br/>The ISA Team</p>";

            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);

            System.out.println("Email poslat!");
        } catch (MessagingException e) {
            throw new MailException("Failed to send email", e) {
            };
        }
    }
}

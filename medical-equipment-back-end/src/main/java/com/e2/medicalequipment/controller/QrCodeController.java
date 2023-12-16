package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.QRCode.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class QrCodeController {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/api/auth/mejl")
    public ResponseEntity<String> getQRCode() {
        String text = "Ovo je podatak koji stoji u qr kodu. Posetite: https://cdn.discordapp.com/attachments/999282155240833135/1185526732707614761/20231215_190439.jpg?ex=658feecf&is=657d79cf&hm=4a8715d7445d5f1976a0c914d863dddfcb353f4de9192ca526ffa355225b5d20&";

        byte[] image = new byte[0];
        try {
            // Generate and Save Qr Code Image in static/image folder
            image = QRCodeGenerator.getQRCodeImage(text, 250, 250);
            QRCodeGenerator.generateQRCodeImage(text, 250, 250, QR_CODE_IMAGE_PATH);

            sendEmailWithQRCode(QR_CODE_IMAGE_PATH);
        } catch (WriterException | IOException | MessagingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("QR Code email sent successfully");
    }

    private void sendEmailWithQRCode(String qrCodeImagePath) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("jblanusa13@gmail.com");
        helper.setSubject("QR Code Email");

        // Set the email body with embedded QR code
        String emailBody = "<html><body><p>Scan the QR code below:</p><img src='cid:qrCodeImage'/><br/><a href='#'>Link Text</a></body></html>";

        // Embed the QR code image in the email body
        helper.setText(emailBody, true);
        helper.addInline("qrCodeImage", new java.io.File(qrCodeImagePath));

        javaMailSender.send(message);
    }
}

package com.e2.medicalequipment.service;

import com.e2.medicalequipment.QRCode.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QRCodeServiceImpl implements QRCodeService{
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public String sendQRCode(String subject, String mail, String message) {
        try {
            QRCodeGenerator.generateQRCodeImage(message, 250, 250, QR_CODE_IMAGE_PATH);

            sendEmailWithQRCode(subject, mail, QR_CODE_IMAGE_PATH);
        } catch (WriterException | IOException | MessagingException e) {
            e.printStackTrace();
        }
        return "QR Code sent successfully";
    }

    private void sendEmailWithQRCode(String subject, String email, String qrCodeImagePath) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject(subject);

        String emailBody = "<html><body><p>Scan the QR code below:</p><img src='cid:qrCodeImage'/><br/><a href='#'>Link Text</a></body></html>";

        helper.setText(emailBody, true);
        helper.addInline("qrCodeImage", new java.io.File(qrCodeImagePath));

        javaMailSender.send(message);
    }
}

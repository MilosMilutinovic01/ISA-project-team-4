package com.e2.medicalequipment.service;

import org.springframework.http.ResponseEntity;

public interface QRCodeService {
    String sendQRCode(String subject, String mail, String message, Long appointmentId);
    String readQRCode(String filepath);
}

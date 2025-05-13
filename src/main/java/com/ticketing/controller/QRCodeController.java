package com.ticketing.controller;

import com.google.zxing.WriterException;
import com.ticketing.util.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QRCodeController {

    private static final String QR_CODE_PATH = "uploads/qr/";

    @GetMapping("/generateQRCode")
    public String generateQRCode(@RequestParam("ticketId") Long ticketId) {
        String qrText = "http://localhost:8080/ticket/details/" + ticketId;
        String qrFilePath = QR_CODE_PATH + "ticket_" + ticketId + ".png";

        try {
            QRCodeUtil.generateQRCode(qrText, qrFilePath);
            return "QR Code generated successfully: " + qrFilePath;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return "Error generating QR Code: " + e.getMessage();
        }
    }
}

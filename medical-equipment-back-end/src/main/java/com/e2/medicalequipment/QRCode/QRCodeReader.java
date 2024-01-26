package com.e2.medicalequipment.QRCode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class QRCodeReader {
    public static String readQRCode(String filePath) throws IOException, NotFoundException {
        BufferedImage image = ImageIO.read(new File(filePath));

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Reader reader = new MultiFormatReader();
        try {
            return reader.decode(bitmap).getText();
        } catch (NotFoundException e) {
            System.out.println("QR Code not found in the image");
            throw e;
        } catch (ChecksumException e) {
            throw new RuntimeException(e);
        } catch (FormatException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.homework.three;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class GPAXmlAdapter extends XmlAdapter<String,Double> {

    private static final String ENCRYPTION_KEY = "encryption_key";

    @Override
    public Double unmarshal(String encryptedGPA) throws Exception {
        String decryptedGpa = decryptString(encryptedGPA);
        return Double.parseDouble(decryptedGpa);
    }

    @Override
    public String marshal(Double GPA) throws Exception {
        String encryptedGpa = encryptString(Double.toString(GPA));
        return encryptedGpa;
    }

    private String decryptString(String data) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(data);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedData = new String(decryptedBytes);
            return decryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String encryptString(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            String encryptedData = Base64.getEncoder().encodeToString(encryptedBytes);
            return encryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

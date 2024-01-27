package org.example.homework.three;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


public class GPAXmlAdapter extends XmlAdapter<String, Double> {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private Key key;

    public GPAXmlAdapter() throws Exception {
        String myEncryptionKey = "ThisIsSpartaThisIsSparta";
        byte[] arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
        key = skf.generateSecret(ks);
    }

    @Override
    public Double unmarshal(String encryptedGPA) throws Exception {
        String decryptedGPA = decrypt(encryptedGPA);
        return Double.parseDouble(decryptedGPA);
    }

    @Override
    public String marshal(Double gpa) throws Exception {
        String encryptedGPA = encrypt(Double.toString(gpa));
        return encryptedGPA;
    }

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(encryptedString);
        return encryptedString;
    }

    public String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(decryptedText);
        return decryptedText;
    }
}

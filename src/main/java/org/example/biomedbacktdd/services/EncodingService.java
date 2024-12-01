package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.results.DecryptedMessageResult;
import org.example.biomedbacktdd.dto.results.EncryptedMessageResult;
import org.example.biomedbacktdd.services.interfaces.encoding.IEncodingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncodingService implements IEncodingService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    @Value("${security.encrypt.token.secret-key:default}")
    private String SECRET_KEY = "";

    @Override
    public EncryptedMessageResult encryptUrl(String url) {
        EncryptedMessageResult response = null;

        try {
            if (url == null || url.isEmpty()) {
                throw new BadCredentialsException("URL cannot be null or empty");
            }

            byte[] iv = generateIV();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH, iv);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
            byte[] encryptedBytes = cipher.doFinal(url.getBytes());

            String encryptedMessage = Base64.getEncoder().encodeToString(iv) +
                    ":" +
                    Base64.getEncoder().encodeToString(encryptedBytes);

            response = new EncryptedMessageResult(encryptedMessage, "URL successfully encrypted");

        } catch (Exception e) {
            return null;
        }

        return response;
    }

    @Override
    public DecryptedMessageResult decryptUrl(String encryptedUrl) {
        DecryptedMessageResult response = null;

        try {
            if (encryptedUrl == null || encryptedUrl.isEmpty()) {
                throw new BadCredentialsException("Encrypted URL cannot be null or empty");
            }

            String[] parts = encryptedUrl.split(":");
            if (parts.length != 2) {
                throw new BadCredentialsException("Invalid encrypted URL format");
            }

            byte[] iv = Base64.getDecoder().decode(parts[0]);
            byte[] encryptedBytes = Base64.getDecoder().decode(parts[1]);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH, iv);

            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            response = new DecryptedMessageResult(new String(decryptedBytes), "URL successfully decrypted");

        } catch (Exception e) {
            return null;
        }

        return response;
    }

    private byte[] generateIV() {
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
}

package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.services.interfaces.other.IOtherService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class OtherService implements IOtherService {

    // Este valor de SECRET_KEY é meramente ilustrativo e para fins de teste.
    private static final String SECRET_KEY = "1234567890123456";

    @Override
    public String encryptUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new BadCredentialsException("URL cannot be null or empty");
        }
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedBytes = cipher.doFinal(url.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new BadCredentialsException("Error encrypting the URL!");
        }
    }

    @Override
    public String decryptUrl(String encryptedUrl) {
        if (encryptedUrl == null || encryptedUrl.isEmpty()) {
            throw new BadCredentialsException("Encrypted URL cannot be null or empty");
        }
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedUrl);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new BadCredentialsException("Error decrypting the URL!");
        }
    }
}

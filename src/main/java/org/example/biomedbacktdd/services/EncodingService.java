package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.results.DecryptedMessageResult;
import org.example.biomedbacktdd.dto.results.EncryptedMessageResult;
import org.example.biomedbacktdd.services.interfaces.encoding.IEncodingService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncodingService implements IEncodingService {

    // Este valor de SECRET_KEY é meramente ilustrativo e para fins de teste.
    private static final String SECRET_KEY = "1234567890123456";

    @Override
    public EncryptedMessageResult encryptUrl(String url) {
        EncryptedMessageResult response = null;

        try {
            if (url == null || url.isEmpty()) {
                throw new BadCredentialsException("URL cannot be null or empty");
            }
            try {
                Cipher cipher = Cipher.getInstance("AES");
                SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);

                byte[] encryptedBytes = cipher.doFinal(url.getBytes());

                response = new EncryptedMessageResult(Base64.getEncoder().encodeToString(encryptedBytes), "URL successfully encrypted");
            } catch (Exception e) {
                throw new BadCredentialsException("Error encrypting the URL!");
            }
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
            try {
                Cipher cipher = Cipher.getInstance("AES");
                SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
                cipher.init(Cipher.DECRYPT_MODE, keySpec);

                byte[] decodedBytes = Base64.getDecoder().decode(encryptedUrl);
                byte[] decryptedBytes = cipher.doFinal(decodedBytes);

                response = new DecryptedMessageResult(new String(decryptedBytes), "URL successfully decrypted");
            } catch (Exception e) {
                throw new BadCredentialsException("Error decrypting the URL!");
            }
        } catch (Exception e) {
            return null;
        }

        return response;
    }
}

package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.results.DecryptedMessageResult;
import org.example.biomedbacktdd.dto.results.EncryptedMessageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class EncodingServiceTest {

    private EncodingService encodingService;

    @BeforeEach
    public void setUp() {
        encodingService = new EncodingService();
        // Injetando a chave secreta personalizada
        // Chave de 16 bytes para AES
        String secretKey = "1234567890123456";
        ReflectionTestUtils.setField(encodingService, "SECRET_KEY", secretKey);
    }

    @Test
    void testEncryptUrlWithValidUrl() {
        // Dado
        String url = "https://example.com";

        // Quando
        EncryptedMessageResult result = encodingService.encryptUrl(url);

        // Então
        assertNotNull(result);
        assertNotNull(result.getEncryptedUrl());
        assertEquals("URL successfully encrypted", result.getMessage());
    }

    @Test
    void testDecryptUrlWithValidEncryptedUrl() {
        // Dado
        String url = "https://example.com";

        // Primeiro, criptografar
        EncryptedMessageResult encryptedResult = encodingService.encryptUrl(url);
        String encryptedUrl = encryptedResult.getEncryptedUrl();

        // Quando
        DecryptedMessageResult decryptedResult = encodingService.decryptUrl(encryptedUrl);

        // Então
        assertNotNull(decryptedResult);
        assertEquals(url, decryptedResult.getDecryptedUrl());
        assertEquals("URL successfully decrypted", decryptedResult.getMessage());
    }

    @Test
    void testEncryptUrlWithNullUrl() {
        // Quando
        EncryptedMessageResult result = encodingService.encryptUrl(null);

        // Então
        assertNull(result);
    }

    @Test
    void testEncryptUrlWithEmptyUrl() {
        // Quando
        EncryptedMessageResult result = encodingService.encryptUrl("");

        // Então
        assertNull(result);
    }

    @Test
    void testDecryptUrlWithNullEncryptedUrl() {
        // Quando
        DecryptedMessageResult result = encodingService.decryptUrl(null);

        // Então
        assertNull(result);
    }

    @Test
    void testDecryptUrlWithEmptyEncryptedUrl() {
        // Quando
        DecryptedMessageResult result = encodingService.decryptUrl("");

        // Então
        assertNull(result);
    }

    @Test
    void testDecryptUrlWithInvalidFormat() {
        // Quando
        DecryptedMessageResult result = encodingService.decryptUrl("invalid_encrypted_url");

        // Então
        assertNull(result);
    }

    @Test
    void testEncryptDecryptCycle() {
        // Dado
        String url = "https://example.com";

        // Quando
        EncryptedMessageResult encryptedResult = encodingService.encryptUrl(url);
        String encryptedUrl = encryptedResult.getEncryptedUrl();
        DecryptedMessageResult decryptedResult = encodingService.decryptUrl(encryptedUrl);

        // Então
        assertNotNull(decryptedResult);
        assertEquals(url, decryptedResult.getDecryptedUrl());
    }
}

package org.example.biomedbacktdd.dto.results;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DecryptedMessageResultTest {

    @Test
    void testConstructorAndGetters() {
        // Given
        String decryptedUrl = "http://example.com/decrypted";
        String message = "Decryption successful";

        // When
        DecryptedMessageResult result = new DecryptedMessageResult(decryptedUrl, message);

        // Then
        assertThat(result.getDecryptedUrl()).isEqualTo(decryptedUrl);
        assertThat(result.getMessage()).isEqualTo(message);
    }

    @Test
    void testSetters() {
        // Given
        DecryptedMessageResult result = new DecryptedMessageResult(null, null);
        String decryptedUrl = "http://example.com/decrypted";
        String message = "Decryption successful";

        // When
        result.setDecryptedUrl(decryptedUrl);
        result.setMessage(message);

        // Then
        assertThat(result.getDecryptedUrl()).isEqualTo(decryptedUrl);
        assertThat(result.getMessage()).isEqualTo(message);
    }
}

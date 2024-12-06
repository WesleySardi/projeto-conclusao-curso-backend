package org.example.biomedbacktdd.dto.results;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EncryptedMessageResultTest {

    @Test
    void testConstructorAndGetters() {
        // Given
        String encryptedUrl = "http://example.com/encrypted";
        String message = "Encryption successful";

        // When
        EncryptedMessageResult result = new EncryptedMessageResult(encryptedUrl, message);

        // Then
        assertThat(result.getEncryptedUrl()).isEqualTo(encryptedUrl);
        assertThat(result.getMessage()).isEqualTo(message);
    }

    @Test
    void testSetters() {
        // Given
        EncryptedMessageResult result = new EncryptedMessageResult(null, null);
        String encryptedUrl = "http://example.com/encrypted";
        String message = "Encryption successful";

        // When
        result.setEncryptedUrl(encryptedUrl);
        result.setMessage(message);

        // Then
        assertThat(result.getEncryptedUrl()).isEqualTo(encryptedUrl);
        assertThat(result.getMessage()).isEqualTo(message);
    }

}

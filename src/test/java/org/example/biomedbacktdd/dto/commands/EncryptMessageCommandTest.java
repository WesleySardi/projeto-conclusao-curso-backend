package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EncryptMessageCommandTest {

    @Test
    void testEmptyConstructor() {
        // When
        EncryptMessageCommand command = new EncryptMessageCommand();

        // Then
        assertThat(command.getUrl()).isNull();
    }

    @Test
    void testConstructorWithUrl() {
        // Given
        String url = "http://example.com";

        // When
        EncryptMessageCommand command = new EncryptMessageCommand(url);

        // Then
        assertThat(command.getUrl()).isEqualTo(url);
    }

    @Test
    void testSetAndGetUrl() {
        // Given
        EncryptMessageCommand command = new EncryptMessageCommand();
        String url = "http://example.com";

        // When
        command.setUrl(url);

        // Then
        assertThat(command.getUrl()).isEqualTo(url);
    }
}

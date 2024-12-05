package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DecryptMessageCommandTest {

    @Test
    void testConstructorWithUrl() {
        // Given
        String expectedUrl = "http://example.com";

        // When
        DecryptMessageCommand command = new DecryptMessageCommand(expectedUrl);

        // Then
        assertThat(command.getUrl()).isEqualTo(expectedUrl);
    }

    @Test
    void testEmptyConstructor() {
        // When
        DecryptMessageCommand command = new DecryptMessageCommand();

        // Then
        assertThat(command.getUrl()).isNull();
    }

    @Test
    void testSetUrl() {
        // Given
        DecryptMessageCommand command = new DecryptMessageCommand();
        String expectedUrl = "http://example.com";

        // When
        command.setUrl(expectedUrl);

        // Then
        assertThat(command.getUrl()).isEqualTo(expectedUrl);
    }

    @Test
    void testGetUrl() {
        // Given
        String expectedUrl = "http://example.com";
        DecryptMessageCommand command = new DecryptMessageCommand(expectedUrl);

        // When
        String actualUrl = command.getUrl();

        // Then
        assertThat(actualUrl).isEqualTo(expectedUrl);
    }
}

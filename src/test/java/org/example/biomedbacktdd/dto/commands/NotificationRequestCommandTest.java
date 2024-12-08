package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationRequestCommandTest {

    @Test
    void testEmptyConstructor() {
        // When
        NotificationRequestCommand command = new NotificationRequestCommand();

        // Then
        assertThat(command.getTitle()).isNull();
        assertThat(command.getBody()).isNull();
        assertThat(command.getCpfResponsavel()).isNull();
        assertThat(command.getCpfDependente()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NotificationRequestCommand command = new NotificationRequestCommand();
        String title = "Notification Title";
        String body = "This is the body of the notification.";
        String cpfResponsavel = "12345678900";
        String cpfDependente = "98765432100";

        // When
        command.setTitle(title);
        command.setBody(body);
        command.setCpfResponsavel(cpfResponsavel);
        command.setCpfDependente(cpfDependente);

        // Then
        assertThat(command.getTitle()).isEqualTo(title);
        assertThat(command.getBody()).isEqualTo(body);
        assertThat(command.getCpfResponsavel()).isEqualTo(cpfResponsavel);
        assertThat(command.getCpfDependente()).isEqualTo(cpfDependente);
    }
}

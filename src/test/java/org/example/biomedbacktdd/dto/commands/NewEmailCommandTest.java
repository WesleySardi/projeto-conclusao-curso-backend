package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class NewEmailCommandTest {

    @Test
    void testConstructorAndGetters() {
        // Given
        int key = 101;
        Timestamp sendDate = Timestamp.valueOf("2023-12-01 10:15:30");
        Timestamp returnDate = Timestamp.valueOf("2023-12-02 15:45:00");
        String emailUser = "user@example.com";
        String cpfDep = "12345678900";

        // When
        NewEmailCommand command = new NewEmailCommand(key, sendDate, returnDate, emailUser, cpfDep);

        // Then
        assertThat(command.getKey()).isEqualTo(key);
        assertThat(command.getSendDate()).isEqualTo(sendDate);
        assertThat(command.getReturnDate()).isEqualTo(returnDate);
        assertThat(command.getEmailUser()).isEqualTo(emailUser);
        assertThat(command.getCpfDep()).isEqualTo(cpfDep);
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewEmailCommand command = new NewEmailCommand(0, null, null, null, null);
        int key = 202;
        Timestamp sendDate = Timestamp.valueOf("2023-12-03 12:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-04 18:30:00");
        String emailUser = "newuser@example.com";
        String cpfDep = "98765432100";

        // When
        command.setKey(key);
        command.setSendDate(sendDate);
        command.setReturnDate(returnDate);
        command.setEmailUser(emailUser);
        command.setCpfDep(cpfDep);

        // Then
        assertThat(command.getKey()).isEqualTo(key);
        assertThat(command.getSendDate()).isEqualTo(sendDate);
        assertThat(command.getReturnDate()).isEqualTo(returnDate);
        assertThat(command.getEmailUser()).isEqualTo(emailUser);
        assertThat(command.getCpfDep()).isEqualTo(cpfDep);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        Timestamp sendDate = Timestamp.valueOf("2023-12-05 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-06 15:00:00");

        NewEmailCommand command1 = new NewEmailCommand(1, sendDate, returnDate, "user@example.com", "12345678900");
        NewEmailCommand command2 = new NewEmailCommand(1, sendDate, returnDate, "user@example.com", "12345678900");
        NewEmailCommand command3 = new NewEmailCommand(2, sendDate, returnDate, "different@example.com", "98765432100");

        // Then
        assertThat(command1).isEqualTo(command2);
        assertThat(command1).hasSameHashCodeAs(command2);
        assertThat(command1).isNotEqualTo(command3);
    }
}

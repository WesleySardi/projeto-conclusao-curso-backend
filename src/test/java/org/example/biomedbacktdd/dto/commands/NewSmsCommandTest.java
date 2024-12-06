package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class NewSmsCommandTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewSmsCommand command = new NewSmsCommand();

        // Then
        assertThat(command.getKey()).isZero();
        assertThat(command.getSendDate()).isNull();
        assertThat(command.getReturnDate()).isNull();
        assertThat(command.getPhoneUser()).isNull();
        assertThat(command.getCpfDep()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewSmsCommand command = new NewSmsCommand();
        int key = 202;
        Timestamp sendDate = Timestamp.valueOf("2023-12-03 12:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-04 18:30:00");
        String phoneUser = "5559876543";
        String cpfDep = "98765432100";

        // When
        command.setKey(key);
        command.setSendDate(sendDate);
        command.setReturnDate(returnDate);
        command.setPhoneUser(phoneUser);
        command.setCpfDep(cpfDep);

        // Then
        assertThat(command.getKey()).isEqualTo(key);
        assertThat(command.getSendDate()).isEqualTo(sendDate);
        assertThat(command.getReturnDate()).isEqualTo(returnDate);
        assertThat(command.getPhoneUser()).isEqualTo(phoneUser);
        assertThat(command.getCpfDep()).isEqualTo(cpfDep);
    }
}

package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeviceStorageCommandTest {

    @Test
    void testEmptyConstructor() {
        // When
        DeviceStorageCommand command = new DeviceStorageCommand();

        // Then
        assertThat(command.getTokenDispositivo()).isNull();
        assertThat(command.getCpfResponsavel()).isNull();
    }

    @Test
    void testSetAndGetTokenDispositivo() {
        // Given
        DeviceStorageCommand command = new DeviceStorageCommand();
        String token = "sampleToken";

        // When
        command.setTokenDispositivo(token);

        // Then
        assertThat(command.getTokenDispositivo()).isEqualTo(token);
    }

    @Test
    void testSetAndGetCpfResponsavel() {
        // Given
        DeviceStorageCommand command = new DeviceStorageCommand();
        String cpf = "12345678900";

        // When
        command.setCpfResponsavel(cpf);

        // Then
        assertThat(command.getCpfResponsavel()).isEqualTo(cpf);
    }

    private void assertEqualsAndHashCode(DeviceStorageCommand command1, DeviceStorageCommand command2) {
        assertThat(command1)
                .isEqualTo(command2)
                .hasSameHashCodeAs(command2);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        DeviceStorageCommand command1 = new DeviceStorageCommand();
        command1.setTokenDispositivo("token1");
        command1.setCpfResponsavel("cpf1");

        DeviceStorageCommand command2 = new DeviceStorageCommand();
        command2.setTokenDispositivo("token1");
        command2.setCpfResponsavel("cpf1");

        DeviceStorageCommand command3 = new DeviceStorageCommand();
        command3.setTokenDispositivo("token2");
        command3.setCpfResponsavel("cpf2");

        // Then
        assertEqualsAndHashCode(command1, command2);
        assertThat(command1).isNotEqualTo(command3);
    }


    @Test
    void testToString() {
        // Given
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setTokenDispositivo("token");
        command.setCpfResponsavel("cpf");

        // Then
        assertThat(command).hasToString("DeviceStorageCommand{tokenDispositivo='token', cpfResponsavel='cpf'}");
    }
}

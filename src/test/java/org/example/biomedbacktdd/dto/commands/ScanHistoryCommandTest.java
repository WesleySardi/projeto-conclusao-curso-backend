package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ScanHistoryCommandTest {

    @Test
    void testSettersAndGetters() {
        // Given
        ScanHistoryCommand command = new ScanHistoryCommand();
        String scanName = "John Doe";
        String scanEmail = "john.doe@example.com";
        String scanPhone = "123456789";
        String depCpf = "12345678900";
        OffsetDateTime scanDateTime = OffsetDateTime.now();
        Double latitude = -23.5505;
        Double longitude = -46.6333;

        // When
        command.setScanName(scanName);
        command.setScanEmail(scanEmail);
        command.setScanPhone(scanPhone);
        command.setDepCpf(depCpf);
        command.setScanDateTime(scanDateTime);
        command.setLatitude(latitude);
        command.setLongitude(longitude);

        // Then
        assertThat(command.getScanName()).isEqualTo(scanName);
        assertThat(command.getScanEmail()).isEqualTo(scanEmail);
        assertThat(command.getScanPhone()).isEqualTo(scanPhone);
        assertThat(command.getDepCpf()).isEqualTo(depCpf);
        assertThat(command.getScanDateTime()).isEqualTo(scanDateTime);
        assertThat(command.getLatitude()).isEqualTo(latitude);
        assertThat(command.getLongitude()).isEqualTo(longitude);
    }

    @Test
    void testEmptyConstructor() {
        // When
        ScanHistoryCommand command = new ScanHistoryCommand();

        // Then
        assertThat(command.getScanName()).isNull();
        assertThat(command.getScanEmail()).isNull();
        assertThat(command.getScanPhone()).isNull();
        assertThat(command.getDepCpf()).isNull();
        assertThat(command.getScanDateTime()).isNull();
        assertThat(command.getLatitude()).isNull();
        assertThat(command.getLongitude()).isNull();
    }
}

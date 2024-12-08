package org.example.biomedbacktdd.dto.viewmodels;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ScanHistoryViewModelTest {

    @Test
    void testEmptyConstructor() {
        // When
        ScanHistoryViewModel viewModel = new ScanHistoryViewModel();

        // Then
        assertThat(viewModel.getScanName()).isNull();
        assertThat(viewModel.getScanEmail()).isNull();
        assertThat(viewModel.getScanPhone()).isNull();
        assertThat(viewModel.getDepCpf()).isNull();
        assertThat(viewModel.getScanDateTime()).isNull();
        assertThat(viewModel.getLatitude()).isNull();
        assertThat(viewModel.getLongitude()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        ScanHistoryViewModel viewModel = new ScanHistoryViewModel();
        String scanName = "John Doe";
        String scanEmail = "john.doe@example.com";
        String scanPhone = "123456789";
        String depCpf = "98765432100";
        OffsetDateTime scanDateTime = OffsetDateTime.now();
        Double latitude = -23.5505;
        Double longitude = -46.6333;

        // When
        viewModel.setScanName(scanName);
        viewModel.setScanEmail(scanEmail);
        viewModel.setScanPhone(scanPhone);
        viewModel.setDepCpf(depCpf);
        viewModel.setScanDateTime(scanDateTime);
        viewModel.setLatitude(latitude);
        viewModel.setLongitude(longitude);

        // Then
        assertThat(viewModel.getScanName()).isEqualTo(scanName);
        assertThat(viewModel.getScanEmail()).isEqualTo(scanEmail);
        assertThat(viewModel.getScanPhone()).isEqualTo(scanPhone);
        assertThat(viewModel.getDepCpf()).isEqualTo(depCpf);
        assertThat(viewModel.getScanDateTime()).isEqualTo(scanDateTime);
        assertThat(viewModel.getLatitude()).isEqualTo(latitude);
        assertThat(viewModel.getLongitude()).isEqualTo(longitude);
    }
}

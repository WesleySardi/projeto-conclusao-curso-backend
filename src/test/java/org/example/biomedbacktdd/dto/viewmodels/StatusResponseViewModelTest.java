package org.example.biomedbacktdd.dto.viewmodels;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusResponseViewModelTest {

    @Test
    void testEmptyConstructor() {
        // When
        StatusResponseViewModel<String> viewModel = new StatusResponseViewModel<>();

        // Then
        assertThat(viewModel.getContentResponse()).isNull();
        assertThat(viewModel.getInfoMessage()).isNull();
        assertThat(viewModel.getStatusMessage()).isNull();
        assertThat(viewModel.getStatus()).isZero();
        assertThat(viewModel.getIsOk()).isNull();
    }

    @Test
    void testConstructorWithAllAttributes() {
        // Given
        String responseContent = "Response content";
        String infoMessage = "Info message";
        String statusMessage = "Status message";
        int status = 200;
        Boolean isOk = true;

        // When
        StatusResponseViewModel<String> viewModel = new StatusResponseViewModel<>(responseContent, infoMessage, statusMessage, status, isOk);

        // Then
        assertThat(viewModel.getContentResponse()).isEqualTo(responseContent);
        assertThat(viewModel.getInfoMessage()).isEqualTo(infoMessage);
        assertThat(viewModel.getStatusMessage()).isEqualTo(statusMessage);
        assertThat(viewModel.getStatus()).isEqualTo(status);
        assertThat(viewModel.getIsOk()).isEqualTo(isOk);
    }

    @Test
    void testSettersAndGetters() {
        // Given
        StatusResponseViewModel<String> viewModel = new StatusResponseViewModel<>();
        String responseContent = "Response content updated";
        String infoMessage = "Updated info";
        String statusMessage = "Updated status";
        int status = 404;
        Boolean isOk = false;

        // When
        viewModel.setContentResponse(responseContent);
        viewModel.setInfoMessage(infoMessage);
        viewModel.setStatusMessage(statusMessage);
        viewModel.setStatus(status);
        viewModel.setIsOk(isOk);

        // Then
        assertThat(viewModel.getContentResponse()).isEqualTo(responseContent);
        assertThat(viewModel.getInfoMessage()).isEqualTo(infoMessage);
        assertThat(viewModel.getStatusMessage()).isEqualTo(statusMessage);
        assertThat(viewModel.getStatus()).isEqualTo(status);
        assertThat(viewModel.getIsOk()).isEqualTo(isOk);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        StatusResponseViewModel<String> viewModel1 = new StatusResponseViewModel<>("Content", "Info", "Status", 200, true);
        StatusResponseViewModel<String> viewModel2 = new StatusResponseViewModel<>("Content", "Info", "Status", 200, true);
        StatusResponseViewModel<String> viewModel3 = new StatusResponseViewModel<>("Different", "Different Info", "Different Status", 404, false);

        // Then
        assertThat(viewModel1).isEqualTo(viewModel2);
        assertThat(viewModel1).hasSameHashCodeAs(viewModel2);
        assertThat(viewModel1).isNotEqualTo(viewModel3);
    }
}

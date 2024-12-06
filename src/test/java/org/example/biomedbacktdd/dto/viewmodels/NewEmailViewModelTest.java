package org.example.biomedbacktdd.dto.viewmodels;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class NewEmailViewModelTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewEmailViewModel viewModel = new NewEmailViewModel();

        // Then
        assertThat(viewModel.getKey()).isEqualTo(0);
        assertThat(viewModel.getSendDate()).isNull();
        assertThat(viewModel.getReturnDate()).isNull();
        assertThat(viewModel.getEmailUser()).isNull();
        assertThat(viewModel.getCpfDep()).isNull();
    }

    @Test
    void testConstructorWithAllAttributes() {
        // Given
        int key = 101;
        Timestamp sendDate = Timestamp.valueOf("2023-12-01 10:15:30");
        Timestamp returnDate = Timestamp.valueOf("2023-12-02 15:45:00");
        String emailUser = "user@example.com";
        String cpfDep = "98765432100";

        // When
        NewEmailViewModel viewModel = new NewEmailViewModel(key, sendDate, returnDate, emailUser, cpfDep);

        // Then
        assertThat(viewModel.getKey()).isEqualTo(key);
        assertThat(viewModel.getSendDate()).isEqualTo(sendDate);
        assertThat(viewModel.getReturnDate()).isEqualTo(returnDate);
        assertThat(viewModel.getEmailUser()).isEqualTo(emailUser);
        assertThat(viewModel.getCpfDep()).isEqualTo(cpfDep);
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewEmailViewModel viewModel = new NewEmailViewModel();
        int key = 202;
        Timestamp sendDate = Timestamp.valueOf("2023-12-03 12:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-04 18:30:00");
        String emailUser = "newuser@example.com";
        String cpfDep = "12345678900";

        // When
        viewModel.setKey(key);
        viewModel.setSendDate(sendDate);
        viewModel.setReturnDate(returnDate);
        viewModel.setEmailUser(emailUser);
        viewModel.setCpfDep(cpfDep);

        // Then
        assertThat(viewModel.getKey()).isEqualTo(key);
        assertThat(viewModel.getSendDate()).isEqualTo(sendDate);
        assertThat(viewModel.getReturnDate()).isEqualTo(returnDate);
        assertThat(viewModel.getEmailUser()).isEqualTo(emailUser);
        assertThat(viewModel.getCpfDep()).isEqualTo(cpfDep);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        Timestamp sendDate = Timestamp.valueOf("2023-12-05 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-06 15:00:00");

        NewEmailViewModel viewModel1 = new NewEmailViewModel(1, sendDate, returnDate, "user@example.com", "98765432100");
        NewEmailViewModel viewModel2 = new NewEmailViewModel(1, sendDate, returnDate, "user@example.com", "98765432100");
        NewEmailViewModel viewModel3 = new NewEmailViewModel(2, sendDate, returnDate, "different@example.com", "12345678900");

        // Then
        assertThat(viewModel1).isEqualTo(viewModel2);
        assertThat(viewModel1).hasSameHashCodeAs(viewModel2);
        assertThat(viewModel1).isNotEqualTo(viewModel3);
    }
}

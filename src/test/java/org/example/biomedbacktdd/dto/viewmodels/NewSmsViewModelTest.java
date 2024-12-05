package org.example.biomedbacktdd.dto.viewmodels;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class NewSmsViewModelTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewSmsViewModel viewModel = new NewSmsViewModel();

        // Then
        assertThat(viewModel.getKey()).isZero();
        assertThat(viewModel.getSendDate()).isNull();
        assertThat(viewModel.getReturnDate()).isNull();
        assertThat(viewModel.getPhoneUser()).isNull();
        assertThat(viewModel.getCpfDep()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewSmsViewModel viewModel = new NewSmsViewModel();
        int key = 101;
        Timestamp sendDate = Timestamp.valueOf("2023-12-01 10:15:30");
        Timestamp returnDate = Timestamp.valueOf("2023-12-02 15:45:00");
        String phoneUser = "123456789";
        String cpfDep = "98765432100";

        // When
        viewModel.setKey(key);
        viewModel.setSendDate(sendDate);
        viewModel.setReturnDate(returnDate);
        viewModel.setPhoneUser(phoneUser);
        viewModel.setCpfDep(cpfDep);

        // Then
        assertThat(viewModel.getKey()).isEqualTo(key);
        assertThat(viewModel.getSendDate()).isEqualTo(sendDate);
        assertThat(viewModel.getReturnDate()).isEqualTo(returnDate);
        assertThat(viewModel.getPhoneUser()).isEqualTo(phoneUser);
        assertThat(viewModel.getCpfDep()).isEqualTo(cpfDep);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        Timestamp sendDate = Timestamp.valueOf("2023-12-05 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-06 15:00:00");

        NewSmsViewModel viewModel1 = new NewSmsViewModel();
        viewModel1.setKey(1);
        viewModel1.setSendDate(sendDate);
        viewModel1.setReturnDate(returnDate);
        viewModel1.setPhoneUser("123456789");
        viewModel1.setCpfDep("98765432100");

        NewSmsViewModel viewModel2 = new NewSmsViewModel();
        viewModel2.setKey(1);
        viewModel2.setSendDate(sendDate);
        viewModel2.setReturnDate(returnDate);
        viewModel2.setPhoneUser("123456789");
        viewModel2.setCpfDep("98765432100");

        NewSmsViewModel viewModel3 = new NewSmsViewModel();
        viewModel3.setKey(2);
        viewModel3.setSendDate(sendDate);
        viewModel3.setReturnDate(returnDate);
        viewModel3.setPhoneUser("987654321");
        viewModel3.setCpfDep("12345678900");

        // Then
        assertThat(viewModel1).isEqualTo(viewModel2);
        assertThat(viewModel1).hasSameHashCodeAs(viewModel2);
        assertThat(viewModel1).isNotEqualTo(viewModel3);
    }
}

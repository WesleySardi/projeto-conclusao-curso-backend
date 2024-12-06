package org.example.biomedbacktdd.dto.results;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class NewSmsResultTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewSmsResult result = new NewSmsResult();

        // Then
        assertThat(result.getKey()).isEqualTo(0);
        assertThat(result.getSendDate()).isNull();
        assertThat(result.getReturnDate()).isNull();
        assertThat(result.getPhoneUser()).isNull();
        assertThat(result.getCpfDep()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewSmsResult result = new NewSmsResult();
        int key = 101;
        Timestamp sendDate = Timestamp.valueOf("2023-12-01 10:15:30");
        Timestamp returnDate = Timestamp.valueOf("2023-12-02 15:45:00");
        String phoneUser = "123456789";
        String cpfDep = "98765432100";

        // When
        result.setKey(key);
        result.setSendDate(sendDate);
        result.setReturnDate(returnDate);
        result.setPhoneUser(phoneUser);
        result.setCpfDep(cpfDep);

        // Then
        assertThat(result.getKey()).isEqualTo(key);
        assertThat(result.getSendDate()).isEqualTo(sendDate);
        assertThat(result.getReturnDate()).isEqualTo(returnDate);
        assertThat(result.getPhoneUser()).isEqualTo(phoneUser);
        assertThat(result.getCpfDep()).isEqualTo(cpfDep);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        Timestamp sendDate = Timestamp.valueOf("2023-12-05 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-06 15:00:00");

        NewSmsResult result1 = new NewSmsResult();
        result1.setKey(1);
        result1.setSendDate(sendDate);
        result1.setReturnDate(returnDate);
        result1.setPhoneUser("123456789");
        result1.setCpfDep("98765432100");

        NewSmsResult result2 = new NewSmsResult();
        result2.setKey(1);
        result2.setSendDate(sendDate);
        result2.setReturnDate(returnDate);
        result2.setPhoneUser("123456789");
        result2.setCpfDep("98765432100");

        NewSmsResult result3 = new NewSmsResult();
        result3.setKey(2);
        result3.setSendDate(sendDate);
        result3.setReturnDate(returnDate);
        result3.setPhoneUser("987654321");
        result3.setCpfDep("12345678900");

        // Then
        assertThat(result1).isEqualTo(result2);
        assertThat(result1).hasSameHashCodeAs(result2);
        assertThat(result1).isNotEqualTo(result3);
    }
}

package org.example.biomedbacktdd.dto.results;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class NewEmailResultTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewEmailResult result = new NewEmailResult();

        // Then
        assertThat(result.getKey()).isZero();
        assertThat(result.getSendDate()).isNull();
        assertThat(result.getReturnDate()).isNull();
        assertThat(result.getEmailUser()).isNull();
        assertThat(result.getCpfDep()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewEmailResult result = new NewEmailResult();
        int key = 101;
        Timestamp sendDate = Timestamp.valueOf("2023-12-01 10:15:30");
        Timestamp returnDate = Timestamp.valueOf("2023-12-02 15:45:00");
        String emailUser = "user@example.com";
        String cpfDep = "12345678900";

        // When
        result.setKey(key);
        result.setSendDate(sendDate);
        result.setReturnDate(returnDate);
        result.setEmailUser(emailUser);
        result.setCpfDep(cpfDep);

        // Then
        assertThat(result.getKey()).isEqualTo(key);
        assertThat(result.getSendDate()).isEqualTo(sendDate);
        assertThat(result.getReturnDate()).isEqualTo(returnDate);
        assertThat(result.getEmailUser()).isEqualTo(emailUser);
        assertThat(result.getCpfDep()).isEqualTo(cpfDep);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        Timestamp sendDate = Timestamp.valueOf("2023-12-05 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2023-12-06 15:00:00");

        NewEmailResult result1 = new NewEmailResult();
        result1.setKey(1);
        result1.setSendDate(sendDate);
        result1.setReturnDate(returnDate);
        result1.setEmailUser("user@example.com");
        result1.setCpfDep("12345678900");

        NewEmailResult result2 = new NewEmailResult();
        result2.setKey(1);
        result2.setSendDate(sendDate);
        result2.setReturnDate(returnDate);
        result2.setEmailUser("user@example.com");
        result2.setCpfDep("12345678900");

        NewEmailResult result3 = new NewEmailResult();
        result3.setKey(2);
        result3.setSendDate(sendDate);
        result3.setReturnDate(returnDate);
        result3.setEmailUser("different@example.com");
        result3.setCpfDep("98765432100");

        // Then
        assertThat(result1).isEqualTo(result2);
        assertThat(result1).hasSameHashCodeAs(result2);
        assertThat(result1).isNotEqualTo(result3);
    }

}

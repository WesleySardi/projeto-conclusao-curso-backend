package org.example.biomedbacktdd.entities.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class EmailHandlerTest {

    private EmailHandler emailHandler;

    @BeforeEach
    void setUp() {
        emailHandler = new EmailHandler();
    }

    @Test
    void testGettersAndSetters() {
        emailHandler.setEmailCode(1);
        assertEquals(1, emailHandler.getEmailCode());

        Timestamp sendDate = Timestamp.valueOf("2023-12-01 10:00:00");
        emailHandler.setSendDate(sendDate);
        assertEquals(sendDate, emailHandler.getSendDate());

        Timestamp returnDate = Timestamp.valueOf("2023-12-02 15:30:00");
        emailHandler.setReturnDate(returnDate);
        assertEquals(returnDate, emailHandler.getReturnDate());

        emailHandler.setEmailUser("testuser@example.com");
        assertEquals("testuser@example.com", emailHandler.getEmailUser());

        emailHandler.setCpfDep("12345678900");
        assertEquals("12345678900", emailHandler.getCpfDep());
    }

    @Test
    void testEmptyConstructor() {
        assertEquals(0, emailHandler.getEmailCode());
        assertNull(emailHandler.getSendDate());
        assertNull(emailHandler.getReturnDate());
        assertNull(emailHandler.getEmailUser());
        assertNull(emailHandler.getCpfDep());
    }

    @Test
    void testEqualsAndHashCode() {
        EmailHandler emailHandler1 = new EmailHandler();
        emailHandler1.setEmailCode(1);
        emailHandler1.setSendDate(Timestamp.valueOf("2023-12-01 10:00:00"));
        emailHandler1.setReturnDate(Timestamp.valueOf("2023-12-02 15:30:00"));
        emailHandler1.setEmailUser("testuser@example.com");
        emailHandler1.setCpfDep("12345678900");

        EmailHandler emailHandler2 = new EmailHandler();
        emailHandler2.setEmailCode(1);
        emailHandler2.setSendDate(Timestamp.valueOf("2023-12-01 10:00:00"));
        emailHandler2.setReturnDate(Timestamp.valueOf("2023-12-02 15:30:00"));
        emailHandler2.setEmailUser("testuser@example.com");
        emailHandler2.setCpfDep("12345678900");

        assertEquals(emailHandler1, emailHandler2);
        assertEquals(emailHandler1.hashCode(), emailHandler2.hashCode());

        emailHandler2.setEmailCode(2);
        assertNotEquals(emailHandler1, emailHandler2);
        assertNotEquals(emailHandler1.hashCode(), emailHandler2.hashCode());
    }

    @Test
    void testHashCodeUniqueness() {
        EmailHandler emailHandler1 = new EmailHandler();
        emailHandler1.setEmailCode(1);

        EmailHandler emailHandler2 = new EmailHandler();
        emailHandler2.setEmailCode(2);

        assertNotEquals(emailHandler1.hashCode(), emailHandler2.hashCode());
    }
}

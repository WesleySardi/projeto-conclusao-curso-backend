package org.example.biomedbacktdd.entities.sms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class SmsHandlerTest {

    private SmsHandler smsHandler;

    @BeforeEach
    void setUp() {
        smsHandler = new SmsHandler();
    }

    @Test
    void testGettersAndSetters() {
        smsHandler.setSmsCode(1001);
        assertEquals(1001, smsHandler.getSmsCode());

        Timestamp sendDate = Timestamp.valueOf("2023-12-01 10:00:00");
        smsHandler.setSendDate(sendDate);
        assertEquals(sendDate, smsHandler.getSendDate());

        Timestamp returnDate = Timestamp.valueOf("2023-12-02 15:00:00");
        smsHandler.setReturnDate(returnDate);
        assertEquals(returnDate, smsHandler.getReturnDate());

        smsHandler.setPhoneUser("123456789");
        assertEquals("123456789", smsHandler.getPhoneUser());

        smsHandler.setCpfDep("12345678900");
        assertEquals("12345678900", smsHandler.getCpfDep());
    }

    @Test
    void testEmptyConstructor() {
        assertEquals(0, smsHandler.getSmsCode());
        assertNull(smsHandler.getSendDate());
        assertNull(smsHandler.getReturnDate());
        assertNull(smsHandler.getPhoneUser());
        assertNull(smsHandler.getCpfDep());
    }

    @Test
    void testEqualsAndHashCode() {
        SmsHandler sh1 = new SmsHandler();
        sh1.setSmsCode(1001);
        sh1.setSendDate(Timestamp.valueOf("2023-12-01 10:00:00"));
        sh1.setReturnDate(Timestamp.valueOf("2023-12-02 15:00:00"));
        sh1.setPhoneUser("123456789");
        sh1.setCpfDep("12345678900");

        SmsHandler sh2 = new SmsHandler();
        sh2.setSmsCode(1001);
        sh2.setSendDate(Timestamp.valueOf("2023-12-01 10:00:00"));
        sh2.setReturnDate(Timestamp.valueOf("2023-12-02 15:00:00"));
        sh2.setPhoneUser("123456789");
        sh2.setCpfDep("12345678900");

        assertEquals(sh1, sh2);
        assertEquals(sh1.hashCode(), sh2.hashCode());

        sh2.setSmsCode(1002);
        assertNotEquals(sh1, sh2);
        assertNotEquals(sh1.hashCode(), sh2.hashCode());
    }

    @Test
    void testMandatoryFields() {
        smsHandler.setSmsCode(1001);
        smsHandler.setSendDate(Timestamp.valueOf("2023-12-01 10:00:00"));
        smsHandler.setPhoneUser("123456789");

        assertNotNull(smsHandler.getSendDate(), "Send Date should not be null");
        assertNotNull(smsHandler.getPhoneUser(), "Phone User should not be null");
    }

    @Test
    void testOptionalFields() {
        smsHandler.setReturnDate(Timestamp.valueOf("2023-12-02 15:00:00"));
        smsHandler.setCpfDep("12345678900");

        assertEquals(Timestamp.valueOf("2023-12-02 15:00:00"), smsHandler.getReturnDate());
        assertEquals("12345678900", smsHandler.getCpfDep());
    }
}

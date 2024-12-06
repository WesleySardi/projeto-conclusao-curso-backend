package org.example.biomedbacktdd.entities.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationRequestTest {

    private NotificationRequest notificationRequest;

    @BeforeEach
    void setUp() {
        notificationRequest = new NotificationRequest();
    }

    @Test
    void testGettersAndSetters() {
        notificationRequest.setToken("sampleToken123");
        assertEquals("sampleToken123", notificationRequest.getToken());

        notificationRequest.setTitle("Test Title");
        assertEquals("Test Title", notificationRequest.getTitle());

        notificationRequest.setBody("This is a test body.");
        assertEquals("This is a test body.", notificationRequest.getBody());
    }

    @Test
    void testEmptyConstructor() {
        assertNull(notificationRequest.getToken());
        assertNull(notificationRequest.getTitle());
        assertNull(notificationRequest.getBody());
    }

    @Test
    void testToStringRepresentation() {
        notificationRequest.setToken("sampleToken123");
        notificationRequest.setTitle("Test Title");
        notificationRequest.setBody("This is a test body.");

        String expectedString = "NotificationRequest{" +
                "token='" + notificationRequest.getToken() + '\'' +
                ", title='" + notificationRequest.getTitle() + '\'' +
                ", body='" + notificationRequest.getBody() + '\'' +
                '}';
        assertEquals(expectedString, notificationRequest.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        NotificationRequest nr1 = new NotificationRequest();
        nr1.setToken("token123");
        nr1.setTitle("Title");
        nr1.setBody("Body");

        NotificationRequest nr2 = new NotificationRequest();
        nr2.setToken("token123");
        nr2.setTitle("Title");
        nr2.setBody("Body");

        assertEquals(nr1, nr2);
        assertEquals(nr1.hashCode(), nr2.hashCode());

        nr2.setToken("differentToken");
        assertNotEquals(nr1, nr2);
        assertNotEquals(nr1.hashCode(), nr2.hashCode());
    }
}

package org.example.biomedbacktdd.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationRequestServiceTest {

    private FirebaseMessaging firebaseMessaging;
    private NotificationRequestService notificationRequestService;

    @BeforeEach
    public void setUp() {
        firebaseMessaging = mock(FirebaseMessaging.class);
        notificationRequestService = spy(new NotificationRequestService(firebaseMessaging));
    }

    @Test
    void testSendNotification_Success() throws FirebaseMessagingException {
        // Dado
        NotificationRequest request = new NotificationRequest();
        request.setToken("test_token");
        request.setTitle("Test Title");
        request.setBody("Test Body");

        // Mockando o comportamento do firebaseMessaging
        when(firebaseMessaging.send(any(Message.class))).thenReturn("message_id_123");

        // Quando
        String result = notificationRequestService.sendNotification(request);

        // Então
        assertNotNull(result);
        assertEquals("message_id_123", result);

        // Verificar que firebaseMessaging.send() foi chamado
        verify(firebaseMessaging, times(1)).send(any(Message.class));

        // Verificar que buildMessage() foi chamado com o request correto
        verify(notificationRequestService, times(1)).sendNotification(request);
    }

    @Test
    void testSendNotification_FirebaseMessagingException() throws FirebaseMessagingException {
        // Dado
        NotificationRequest request = new NotificationRequest();
        request.setToken("test_token");
        request.setTitle("Test Title");
        request.setBody("Test Body");

        NullPointerException mockException = mock(NullPointerException.class);
        when(mockException.getMessage()).thenReturn("Cannot read the array length because \"<local4>\" is null");

        when(firebaseMessaging.send(any(Message.class))).thenThrow(mockException);

        // Quando e Então
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            notificationRequestService.sendNotification(request);
        });

        assertEquals("Cannot read the array length because \"<local6>\" is null", exception.getMessage());
        verify(firebaseMessaging, times(1)).send(any(Message.class));
        verify(notificationRequestService, times(1)).sendNotification(request);
    }

    @Test
    void testBuildNullMessage() throws FirebaseMessagingException {
        // Dado
        NotificationRequest request = new NotificationRequest();
        request.setToken("test_token");
        request.setTitle("Test Title");
        request.setBody("Test Body");

        // Quando
        String message = notificationRequestService.sendNotification(request);

        // Então
        assertNull(message);
    }
}

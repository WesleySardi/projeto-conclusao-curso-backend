package org.example.biomedbacktdd.handler.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.example.biomedbacktdd.handlers.notification.NotificationRequestHandler;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationRequestHandlerTest {

    @Mock
    private INotificationRequestService notificationRequestService;

    @InjectMocks
    private NotificationRequestHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("handleSendNotification - Sucesso (200)")
    void testHandleSendNotification_Success() throws FirebaseMessagingException {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setToken("token_12345");
        request.setTitle("Test Notification");
        request.setBody("This is a test notification.");

        String expectedMessageId = "msg_123456";

        when(notificationRequestService.sendNotification(any(NotificationRequest.class))).thenReturn(expectedMessageId);

        // Act
        ResponseEntity<StatusResponseViewModel<String>> response = handler.handleSendNotification(request);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");

        StatusResponseViewModel<String> responseBody = response.getBody();
        assertNotNull(responseBody, "O corpo da resposta não deve ser nulo");
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Notificação enviada com sucesso.", responseBody.getInfoMessage(), "O infoMessage deve ser 'Notificação enviada com sucesso.'");
        assertEquals("Sucesso", responseBody.getStatusMessage(), "O statusMessage deve ser 'Sucesso'");
        assertEquals(expectedMessageId, responseBody.getContentResponse(), "O contentResponse deve conter o messageId esperado");

        // Verifica se o serviço foi chamado corretamente
        verify(notificationRequestService, times(1)).sendNotification(request);
    }

    @Test
    @DisplayName("handleSendNotification - Erro Interno do Servidor (500)")
    void testHandleSendNotification_InternalServerError() throws FirebaseMessagingException {
        NotificationRequest request = new NotificationRequest();
        request.setToken("token_12345");
        request.setTitle("Test Notification");
        request.setBody("This is a test notification.");

        String exceptionMessage = "Falha ao enviar a notificação.";

        FirebaseMessagingException exception = mock(FirebaseMessagingException.class);
        when(exception.getMessage()).thenReturn(exceptionMessage);

        when(notificationRequestService.sendNotification(any(NotificationRequest.class)))
                .thenThrow(exception);

        ResponseEntity<StatusResponseViewModel<String>> response = handler.handleSendNotification(request);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");

        StatusResponseViewModel<?> responseBody = response.getBody();
        assertNotNull(responseBody, "O corpo da resposta não deve ser nulo");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro", responseBody.getInfoMessage(), "O infoMessage deve ser 'Erro'");
        assertEquals(exceptionMessage, responseBody.getStatusMessage(), "O statusMessage deve conter a mensagem da exceção");
        assertNull(responseBody.getContentResponse(), "O contentResponse deve ser nulo em caso de erro");

        verify(notificationRequestService, times(1)).sendNotification(request);
    }
}

package org.example.biomedbacktdd.handler.notification;

import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.notification.NotificationFacadeHandler;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationFacadeService;
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

class NotificationFacadeHandlerTest {

    @Mock
    private INotificationFacadeService notificationFacadeService;

    @InjectMocks
    private NotificationFacadeHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("handleSendAndStoreNotification - Sucesso (200)")
    void testHandleSendAndStoreNotification_Success() {
        NotificationRequestCommand command = new NotificationRequestCommand();
        command.setTitle("Test Notification");
        command.setBody("This is a test notification.");
        command.setCpfResponsavel("12345678900");

        doNothing().when(notificationFacadeService).sendAndStoreNotification(any(NotificationRequestCommand.class));

        ResponseEntity<StatusResponseViewModel<NotificationRequestCommand>> response = handler.handleSendAndStoreNotification(command);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<NotificationRequestCommand> responseBody = response.getBody();
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Notificação enviada e armazenada com sucesso.", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Notificação enviada e armazenada com sucesso.'");
        assertEquals("Success", responseBody.getStatusMessage(), "O statusMessage no corpo deve ser 'Success'");
        assertEquals(command, responseBody.getContentResponse(), "Os dados retornados devem corresponder ao comando enviado");

        verify(notificationFacadeService, times(1)).sendAndStoreNotification(command);
    }


    @Test
    @DisplayName("handleSendAndStoreNotification - Erro Interno do Servidor (500)")
    void testHandleSendAndStoreNotification_InternalServerError() {
        NotificationRequestCommand command = new NotificationRequestCommand();
        command.setTitle("Test Notification");
        command.setBody("This is a test notification.");
        command.setCpfResponsavel("12345678900");

        String exceptionMessage = "Falha ao enviar a notificação.";
        doThrow(new RuntimeException(exceptionMessage)).when(notificationFacadeService).sendAndStoreNotification(any(NotificationRequestCommand.class));

        ResponseEntity<StatusResponseViewModel<NotificationRequestCommand>> response = handler.handleSendAndStoreNotification(command);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<NotificationRequestCommand> responseBody = response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao enviar e armazenar notificação: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage no corpo deve corresponder à mensagem de erro");
        assertEquals("Error", responseBody.getStatusMessage(), "O statusMessage no corpo deve ser 'Error'");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        verify(notificationFacadeService, times(1)).sendAndStoreNotification(command);
    }


    @Test
    @DisplayName("handleSendAndStoreNotification - Dados Nulos (500)")
    void testHandleSendAndStoreNotification_NullData() {
        String exceptionMessage = "NotificationRequestCommand não pode ser nulo.";

        doThrow(new RuntimeException(exceptionMessage)).when(notificationFacadeService).sendAndStoreNotification(isNull());

        ResponseEntity<StatusResponseViewModel<NotificationRequestCommand>> response = handler.handleSendAndStoreNotification(null);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<NotificationRequestCommand> responseBody = response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao enviar e armazenar notificação: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage no corpo deve corresponder à mensagem de erro");
        assertEquals("Error", responseBody.getStatusMessage(), "O statusMessage no corpo deve ser 'Error'");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        verify(notificationFacadeService, times(1)).sendAndStoreNotification(null);
    }


    @Test
    @DisplayName("handleSendAndStoreNotification - Requisição Inválida (500)")
    void testHandleSendAndStoreNotification_InvalidRequest() {
        NotificationRequestCommand command = new NotificationRequestCommand();
        command.setTitle("");
        command.setBody("");
        command.setCpfResponsavel("");

        String exceptionMessage = "Dados da notificação inválidos.";
        doThrow(new RuntimeException(exceptionMessage)).when(notificationFacadeService).sendAndStoreNotification(any(NotificationRequestCommand.class));

        ResponseEntity<StatusResponseViewModel<NotificationRequestCommand>> response = handler.handleSendAndStoreNotification(command);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<NotificationRequestCommand> responseBody = response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao enviar e armazenar notificação: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage no corpo deve corresponder à mensagem de erro");
        assertEquals("Error", responseBody.getStatusMessage(), "O statusMessage no corpo deve ser 'Error'");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        verify(notificationFacadeService, times(1)).sendAndStoreNotification(command);
    }

}
package org.example.biomedbacktdd.controller.notification;

import org.example.biomedbacktdd.controllers.notification.NotificationFacadeController;
import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;
import org.example.biomedbacktdd.dto.results.NotificationStorageResult;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.notification.NotificationFacadeHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationFacadeControllerTest {

    @Mock
    private NotificationFacadeHandler notificationFacadeHandler;

    @InjectMocks
    private NotificationFacadeController controller;

    NotificationFacadeControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("POST /api/notifications/send-and-store - Sucesso (200)")
    void testSendAndStoreNotification_Success() {

        NotificationRequestCommand command = new NotificationRequestCommand();

        StatusResponseViewModel<NotificationRequestCommand> successResponse = new StatusResponseViewModel<>();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Notification sent and stored successfully.");

        when(notificationFacadeHandler.handleSendAndStoreNotification(any(NotificationRequestCommand.class)))
                .thenReturn(ResponseEntity.ok(successResponse));


        ResponseEntity<StatusResponseViewModel<NotificationRequestCommand>> response = controller.sendAndStoreNotification(command);


        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta deve ser 200");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");
        assertEquals(200, response.getBody().getStatus(), "O status no corpo deve ser 200");
        assertEquals("Notification sent and stored successfully.", response.getBody().getStatusMessage(),
                "A mensagem de sucesso deve corresponder");

        verify(notificationFacadeHandler, times(1)).handleSendAndStoreNotification(command);
    }

    @Test
    @DisplayName("POST /api/notifications/send-and-store - Requisição Inválida (400)")
    void testSendAndStoreNotification_BadRequest() {

        NotificationRequestCommand command = new NotificationRequestCommand();


        when(notificationFacadeHandler.handleSendAndStoreNotification(any(NotificationRequestCommand.class)))
                .thenReturn(ResponseEntity.badRequest().build());


        ResponseEntity<StatusResponseViewModel<NotificationRequestCommand>> response = controller.sendAndStoreNotification(command);


        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(400, response.getStatusCodeValue(), "O status da resposta deve ser 400");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Bad Request");

        verify(notificationFacadeHandler, times(1)).handleSendAndStoreNotification(command);
    }

    @Test
    @DisplayName("POST /api/notifications/send-and-store - Erro Interno do Servidor (500)")
    void testSendAndStoreNotification_InternalServerError() {

        NotificationRequestCommand command = new NotificationRequestCommand();

        when(notificationFacadeHandler.handleSendAndStoreNotification(any(NotificationRequestCommand.class)))
                .thenReturn(ResponseEntity.status(500).build());


        ResponseEntity<StatusResponseViewModel<NotificationRequestCommand>> response = controller.sendAndStoreNotification(command);


        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(500, response.getStatusCodeValue(), "O status da resposta deve ser 500");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Internal Server Error");

        verify(notificationFacadeHandler, times(1)).handleSendAndStoreNotification(command);
    }
}


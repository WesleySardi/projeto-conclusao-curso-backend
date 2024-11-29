package org.example.biomedbacktdd.controller.notification;

import org.example.biomedbacktdd.controllers.notification.NotificationRequestController;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.example.biomedbacktdd.handlers.notification.NotificationRequestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationRequestControllerTest {

    @Mock
    private NotificationRequestHandler notificationRequestHandler;

    @InjectMocks
    private NotificationRequestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Teste para o método POST /api/notifications/send - Sucesso (200)
     */
    @Test
    @DisplayName("POST /api/notifications/send - Sucesso (200)")
    void testSendNotification_Success() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        // Configure os campos do request conforme necessário
        request.setToken("valid_device_token");
        request.setTitle("Test Notification");
        request.setBody("This is a test notification.");
        // Adicione outros campos se existirem

        StatusResponseViewModel successResponse = new StatusResponseViewModel();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Notification sent successfully.");

        when(notificationRequestHandler.handleSendNotification(any(NotificationRequest.class)))
                .thenReturn(ResponseEntity.ok(successResponse));

        // Act
        ResponseEntity<StatusResponseViewModel<String>> response = controller.sendNotification(request);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta deve ser 200");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");
        assertEquals(200, response.getBody().getStatus(), "O status no corpo deve ser 200");
        assertEquals("Notification sent successfully.", response.getBody().getStatusMessage(),
                "A mensagem de sucesso deve corresponder");

        // Verifica se o handler foi chamado corretamente
        verify(notificationRequestHandler, times(1)).handleSendNotification(request);
    }

    /**
     * Teste para o método POST /api/notifications/send - Erro Interno do Servidor (500)
     */
    @Test
    @DisplayName("POST /api/notifications/send - Erro Interno do Servidor (500)")
    void testSendNotification_InternalServerError() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        // Configure os campos do request conforme necessário
        request.setToken("valid_device_token");
        request.setTitle("Test Notification");
        request.setBody("This is a test notification.");
        // Adicione outros campos se existirem

        when(notificationRequestHandler.handleSendNotification(any(NotificationRequest.class)))
                .thenReturn(ResponseEntity.status(500).build());

        // Act
        ResponseEntity<StatusResponseViewModel<String>> response = controller.sendNotification(request);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(500, response.getStatusCodeValue(), "O status da resposta deve ser 500");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Internal Server Error");

        // Verifica se o handler foi chamado corretamente
        verify(notificationRequestHandler, times(1)).handleSendNotification(request);
    }

    /**
     * Teste para o método POST /api/notifications/send - Dados Nulos (400)
     */
    @Test
    @DisplayName("POST /api/notifications/send - Dados Nulos (400)")
    void testSendNotification_NullData() {
        // Arrange
        NotificationRequest request = null;

        // Configura o mock para retornar Bad Request quando recebe qualquer valor (incluindo null)
        when(notificationRequestHandler.handleSendNotification(any()))
                .thenReturn(ResponseEntity.badRequest().build());

        // Act
        ResponseEntity<StatusResponseViewModel<String>> response = controller.sendNotification(request);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(400, response.getStatusCodeValue(), "O status da resposta deve ser 400");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Bad Request");

        // Verifica se o handler foi chamado corretamente com request = null
        verify(notificationRequestHandler, times(1)).handleSendNotification(request);
    }

    /**
     * Teste para o método POST /api/notifications/send - Requisição Inválida (400)
     * Exemplo: Device Token inválido ou campos obrigatórios ausentes
     */
    @Test
    @DisplayName("POST /api/notifications/send - Requisição Inválida (400)")
    void testSendNotification_InvalidRequest() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        // Configure os campos do request com dados inválidos
        request.setToken(""); // Device Token vazio
        request.setTitle(""); // Título vazio
        request.setBody(""); // Corpo vazio
        // Adicione outros campos inválidos se existirem

        when(notificationRequestHandler.handleSendNotification(any(NotificationRequest.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        // Act
        ResponseEntity<StatusResponseViewModel<String>> response = controller.sendNotification(request);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(400, response.getStatusCodeValue(), "O status da resposta deve ser 400");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Bad Request");

        // Verifica se o handler foi chamado corretamente
        verify(notificationRequestHandler, times(1)).handleSendNotification(request);
    }
}

package org.example.biomedbacktdd.controller.notification;

import org.example.biomedbacktdd.controllers.notification.NotificationStorageController;
import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.notification.NotificationStorageHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationStorageControllerTest {

    @Mock
    private NotificationStorageHandler notificationStorageHandler;

    @InjectMocks
    private NotificationStorageController controller;

    // Inicializa os mocks antes de cada teste
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste para o método POST /api/notifications - Sucesso (200)
    @Test
    @DisplayName("POST /api/notifications - Sucesso (200)")
    void testCreateNotification_Success() {
        // Arrange
        NotificationStorageCommand command = new NotificationStorageCommand();
        // Configure os campos do command conforme necessário
        command.setTitulo("Título da Notificação");
        command.setMensagem("Mensagem da notificação");
        command.setCpfResponsavel("12345678900");
        command.setDataEnvio(ZonedDateTime.now()); // Exemplo de data no formato ISO

        StatusResponseViewModel<NotificationStorageCommand> successResponse = new StatusResponseViewModel();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Notification created successfully.");

        when(notificationStorageHandler.handleCreate(any(NotificationStorageCommand.class)))
                .thenReturn(ResponseEntity.ok(successResponse));

        // Act
        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = controller.createNotification(command);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta deve ser 200");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");
        assertEquals(200, response.getBody().getStatus(), "O status no corpo deve ser 200");
        assertEquals("Notification created successfully.", response.getBody().getStatusMessage(),
                "A mensagem de sucesso deve corresponder");

        verify(notificationStorageHandler, times(1)).handleCreate(command);
    }

    @Test
    @DisplayName("POST /api/notifications - Requisição Inválida (400)")
    void testCreateNotification_BadRequest() {

        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("");
        command.setMensagem("");
        command.setCpfResponsavel("");
        command.setDataEnvio(null);

        when(notificationStorageHandler.handleCreate(any(NotificationStorageCommand.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = controller.createNotification(command);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(400, response.getStatusCodeValue(), "O status da resposta deve ser 400");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Bad Request");

        verify(notificationStorageHandler, times(1)).handleCreate(command);
    }

    @Test
    @DisplayName("POST /api/notifications - Erro Interno do Servidor (500)")
    void testCreateNotification_InternalServerError() {
        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("Título da Notificação");
        command.setMensagem("Mensagem da notificação");
        command.setCpfResponsavel("12345678900");
        command.setDataEnvio(ZonedDateTime.now());

        when(notificationStorageHandler.handleCreate(any(NotificationStorageCommand.class)))
                .thenReturn(ResponseEntity.status(500).build());

        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = controller.createNotification(command);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(500, response.getStatusCodeValue(), "O status da resposta deve ser 500");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Internal Server Error");

        verify(notificationStorageHandler, times(1)).handleCreate(command);
    }

    @Test
    @DisplayName("DELETE /api/notifications/{id} - Sucesso (200)")
    void testDeleteNotification_Success() {
        int id = 1;

        StatusResponseViewModel<NotificationStorageCommand> successResponse = new StatusResponseViewModel();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Notification deleted successfully.");

        when(notificationStorageHandler.handleDelete(id))
                .thenReturn(ResponseEntity.ok(successResponse));

        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = controller.deleteNotification(id);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta deve ser 200");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");
        assertEquals(200, response.getBody().getStatus(), "O status no corpo deve ser 200");
        assertEquals("Notification deleted successfully.", response.getBody().getStatusMessage(),
                "A mensagem de sucesso deve corresponder");

        verify(notificationStorageHandler, times(1)).handleDelete(id);
    }

    @Test
    @DisplayName("DELETE /api/notifications/{id} - Não Encontrado (404)")
    void testDeleteNotification_NotFound() {
        // Arrange
        int id = 999;

        when(notificationStorageHandler.handleDelete(id))
                .thenReturn(ResponseEntity.notFound().build());

        // Act
        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = controller.deleteNotification(id);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(404, response.getStatusCodeValue(), "O status da resposta deve ser 404");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Not Found");

        // Verifica se o handler foi chamado corretamente
        verify(notificationStorageHandler, times(1)).handleDelete(id);
    }

    @Test
    @DisplayName("DELETE /api/notifications/{id} - Erro Interno do Servidor (500)")
    void testDeleteNotification_InternalServerError() {
        int id = 1;

        when(notificationStorageHandler.handleDelete(id))
                .thenReturn(ResponseEntity.status(500).build());

        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = controller.deleteNotification(id);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(500, response.getStatusCodeValue(), "O status da resposta deve ser 500");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Internal Server Error");

        verify(notificationStorageHandler, times(1)).handleDelete(id);
    }

    @Test
    @DisplayName("GET /api/notifications/responsavel/{cpfResponsavel} - Sucesso (200)")
    void testGetNotificationsByResponsavel_Success() {

        String cpfResponsavel = "12345678900";

        StatusResponseViewModel<List<NotificationStorageCommand>> successResponse = new StatusResponseViewModel();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Notifications retrieved successfully.");

        when(notificationStorageHandler.handleGetByResponsavel(cpfResponsavel))
                .thenReturn(ResponseEntity.ok(successResponse));

        ResponseEntity<StatusResponseViewModel<List<NotificationStorageCommand>>> response = controller.getNotificationsByResponsavel(cpfResponsavel);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta deve ser 200");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");
        assertEquals(200, response.getBody().getStatus(), "O status no corpo deve ser 200");
        assertEquals("Notifications retrieved successfully.", response.getBody().getStatusMessage(),
                "A mensagem de sucesso deve corresponder");

        verify(notificationStorageHandler, times(1)).handleGetByResponsavel(cpfResponsavel);
    }

    @Test
    @DisplayName("GET /api/notifications/responsavel/{cpfResponsavel} - Sem Conteúdo (204)")
    void testGetNotificationsByResponsavel_NoContent() {

        String cpfResponsavel = "00000000000";

        when(notificationStorageHandler.handleGetByResponsavel(cpfResponsavel))
                .thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<StatusResponseViewModel<List<NotificationStorageCommand>>> response = controller.getNotificationsByResponsavel(cpfResponsavel);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(204, response.getStatusCodeValue(), "O status da resposta deve ser 204");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para No Content");

        verify(notificationStorageHandler, times(1)).handleGetByResponsavel(cpfResponsavel);
    }

    @Test
    @DisplayName("GET /api/notifications/responsavel/{cpfResponsavel} - Erro Interno do Servidor (500)")
    void testGetNotificationsByResponsavel_InternalServerError() {

        String cpfResponsavel = "12345678900";

        when(notificationStorageHandler.handleGetByResponsavel(cpfResponsavel))
                .thenReturn(ResponseEntity.status(500).build());

        ResponseEntity<StatusResponseViewModel<List<NotificationStorageCommand>>> response = controller.getNotificationsByResponsavel(cpfResponsavel);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(500, response.getStatusCodeValue(), "O status da resposta deve ser 500");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Internal Server Error");

        verify(notificationStorageHandler, times(1)).handleGetByResponsavel(cpfResponsavel);
    }

    @Test
    @DisplayName("GET /api/notifications/responsavel/{cpfResponsavel} - Dados Nulos (400)")
    void testGetNotificationsByResponsavel_NullData() {
        String cpfResponsavel = null;

        when(notificationStorageHandler.handleGetByResponsavel(any()))
                .thenReturn(ResponseEntity.badRequest().build());

        ResponseEntity<StatusResponseViewModel<List<NotificationStorageCommand>>> response = controller.getNotificationsByResponsavel(cpfResponsavel);

        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(400, response.getStatusCodeValue(), "O status da resposta deve ser 400");
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo para Bad Request");

        verify(notificationStorageHandler, times(1)).handleGetByResponsavel(cpfResponsavel);
    }

}

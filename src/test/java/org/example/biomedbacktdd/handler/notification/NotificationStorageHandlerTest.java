package org.example.biomedbacktdd.handler.notification;

import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.notification.NotificationStorage;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.handlers.notification.NotificationStorageHandler;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationStorageService;
import org.example.biomedbacktdd.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class NotificationStorageHandlerTest {

    @Mock
    private INotificationStorageService notificationStorageService;

    @Mock
    private MapperUtil mapperUtil;

    @InjectMocks
    private NotificationStorageHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("handleCreate - Sucesso (200)")
    void testHandleCreate_Success() {
        // Arrange
        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("New Notification");
        command.setMensagem("This is a new notification.");
        command.setCpfResponsavel("12345678900");
        command.setDataEnvio(ZonedDateTime.now());
        command.setLida(false);

        NotificationStorage notificationStorage = new NotificationStorage();
        notificationStorage.setIdNotificacao(1);
        notificationStorage.setTitulo("New Notification");
        notificationStorage.setMensagem("This is a new notification.");
        notificationStorage.setDataEnvio(ZonedDateTime.now());
        notificationStorage.setLida(false);
        Responsible responsavel = new Responsible();
        responsavel.setCpfRes("12345678900");
        responsavel.setNomeRes("John Doe");
        notificationStorage.setResponsavel(responsavel);

        NotificationStorageCommand mappedCommand = new NotificationStorageCommand();
        mappedCommand.setTitulo(notificationStorage.getTitulo());
        mappedCommand.setMensagem(notificationStorage.getMensagem());
        mappedCommand.setCpfResponsavel(notificationStorage.getResponsavel().getCpfRes());
        mappedCommand.setDataEnvio(notificationStorage.getDataEnvio());
        mappedCommand.setLida(notificationStorage.getLida());

        when(notificationStorageService.storeNotification(any(NotificationStorageCommand.class))).thenReturn(notificationStorage);
        when(mapperUtil.map(any(NotificationStorage.class), eq(NotificationStorageCommand.class))).thenReturn(mappedCommand);

        // Act
        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = handler.handleCreate(command);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<NotificationStorageCommand> responseBody = response.getBody();
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Notificação criada com sucesso.", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Notificação criada com sucesso.'");

        assertNull(responseBody.getStatusMessage(), "O statusMessage no corpo deve ser nulo (não configurado)");
        assertEquals(mappedCommand, responseBody.getContentResponse(), "Os dados retornados devem corresponder ao comando mapeado");

        verify(notificationStorageService, times(1)).storeNotification(command);
        verify(mapperUtil, times(1)).map(notificationStorage, NotificationStorageCommand.class);
    }


    @Test
    @DisplayName("handleCreate - Erro Interno do Servidor (500)")
    void testHandleCreate_InternalServerError() {
        // Arrange
        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("New Notification");
        command.setMensagem("This is a new notification.");
        command.setCpfResponsavel("12345678900");
        command.setDataEnvio(ZonedDateTime.now());
        command.setLida(false);

        String exceptionMessage = "Erro ao armazenar a notificação.";
        when(notificationStorageService.storeNotification(any(NotificationStorageCommand.class)))
                .thenThrow(new RuntimeException(exceptionMessage));

        // Act
        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = handler.handleCreate(command);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<NotificationStorageCommand> responseBody = response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao criar notificação: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage no corpo deve corresponder à mensagem de erro");
        assertNull(responseBody.getStatusMessage(), "O statusMessage no corpo deve ser nulo (não configurado)");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        verify(notificationStorageService, times(1)).storeNotification(command);
        verify(mapperUtil, times(0)).map(any(NotificationStorage.class), eq(NotificationStorageCommand.class));
    }


    @Test
    @DisplayName("handleDelete - Sucesso (200)")
    void testHandleDelete_Success() {
        // Arrange
        int notificationId = 1;

        // Configura o mock para não fazer nada quando deleteNotification for chamado
        doNothing().when(notificationStorageService).deleteNotification(notificationId);

        // Act
        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = handler.handleDelete(notificationId);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");

        StatusResponseViewModel<NotificationStorageCommand> responseBody = response.getBody();
        assertNotNull(responseBody, "O corpo da resposta não deve ser nulo");
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Notificação removida com sucesso.", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Notificação removida com sucesso.'");
        assertNull(responseBody.getStatusMessage(), "O statusMessage no corpo deve ser nulo (não configurado)");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos para operações de delete");

        // Verifica se o método deleteNotification foi chamado exatamente uma vez com o parâmetro correto
        verify(notificationStorageService, times(1)).deleteNotification(notificationId);
    }

    @Test
    @DisplayName("handleDelete - Erro Interno do Servidor (500)")
    void testHandleDelete_InternalServerError() {
        // Arrange
        int notificationId = 1;

        String exceptionMessage = "Erro ao remover a notificação.";

        doThrow(new RuntimeException(exceptionMessage)).when(notificationStorageService).deleteNotification(notificationId);

        // Act
        ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> response = handler.handleDelete(notificationId);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<NotificationStorageCommand> responseBody = response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao remover notificação: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage no corpo deve corresponder à mensagem de erro");
        assertNull(responseBody.getStatusMessage(), "O statusMessage no corpo deve ser nulo (não configurado)");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        // Verifica se o serviço foi chamado corretamente
        verify(notificationStorageService, times(1)).deleteNotification(notificationId);
    }


    @Test
    @DisplayName("handleGetByResponsavel - Sucesso (200)")
    void testHandleGetByResponsavel_Success() {
        // Arrange
        String cpfResponsavel = "12345678900";

        Responsible responsavel = getResponsible(cpfResponsavel);

        NotificationStorage notification1 = new NotificationStorage();
        notification1.setIdNotificacao(1);
        notification1.setTitulo("Notification 1");
        notification1.setMensagem("Body 1");
        notification1.setDataEnvio(ZonedDateTime.now());
        notification1.setLida(false);
        notification1.setResponsavel(responsavel);

        NotificationStorage notification2 = new NotificationStorage();
        notification2.setIdNotificacao(2);
        notification2.setTitulo("Notification 2");
        notification2.setMensagem("Body 2");
        notification2.setDataEnvio(ZonedDateTime.now());
        notification2.setLida(true);
        notification2.setResponsavel(responsavel);

        List<NotificationStorage> notifications = Arrays.asList(notification1, notification2);

        NotificationStorageCommand command1 = new NotificationStorageCommand();
        command1.setTitulo(notification1.getTitulo());
        command1.setMensagem(notification1.getMensagem());
        command1.setCpfResponsavel(notification1.getResponsavel().getCpfRes());
        command1.setDataEnvio(notification1.getDataEnvio());
        command1.setLida(notification1.getLida());

        NotificationStorageCommand command2 = new NotificationStorageCommand();
        command2.setTitulo(notification2.getTitulo());
        command2.setMensagem(notification2.getMensagem());
        command2.setCpfResponsavel(notification2.getResponsavel().getCpfRes());
        command2.setDataEnvio(notification2.getDataEnvio());
        command2.setLida(notification2.getLida());

        List<NotificationStorageCommand> mappedCommands = Arrays.asList(command1, command2);

        when(notificationStorageService.getNotificationsByResponsavel(cpfResponsavel)).thenReturn(notifications);
        when(mapperUtil.map(notification1, NotificationStorageCommand.class)).thenReturn(command1);
        when(mapperUtil.map(notification2, NotificationStorageCommand.class)).thenReturn(command2);

        // Act
        ResponseEntity<StatusResponseViewModel<List<NotificationStorageCommand>>> response = handler.handleGetByResponsavel(cpfResponsavel);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<List<NotificationStorageCommand>> responseBody = response.getBody();
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals("Notificações encontradas com sucesso.", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Notificações encontradas com sucesso.'");
        assertEquals("Success", responseBody.getStatusMessage(), "O statusMessage no corpo deve ser 'Success'");
        assertEquals(mappedCommands, responseBody.getContentResponse(), "Os dados retornados devem corresponder à lista de comandos mapeados");

        verify(notificationStorageService, times(1)).getNotificationsByResponsavel(cpfResponsavel);
        verify(mapperUtil, times(1)).map(notification1, NotificationStorageCommand.class);
        verify(mapperUtil, times(1)).map(notification2, NotificationStorageCommand.class);
    }


    private static Responsible getResponsible(String cpfResponsavel) {
        Responsible responsavel = new Responsible();
        responsavel.setCpfRes(cpfResponsavel);
        responsavel.setNomeRes("John Doe");
        responsavel.setIdadeRes(30);
        responsavel.setContato1Res("123456789");
        responsavel.setContato2Res("987654321");
        responsavel.setContato3Res("555555555");
        responsavel.setPlanoAssinado(1);
        responsavel.setEmailRes("john.doe@example.com");
        responsavel.setRgRes("MG1234567");
        return responsavel;
    }

    @Test
    @DisplayName("handleGetByResponsavel - Erro Interno do Servidor (500)")
    void testHandleGetByResponsavel_InternalServerError() {
        // Arrange
        String cpfResponsavel = "12345678900";

        String exceptionMessage = "Erro ao buscar notificações.";

        when(notificationStorageService.getNotificationsByResponsavel(cpfResponsavel))
                .thenThrow(new RuntimeException(exceptionMessage));

        // Act
        ResponseEntity<StatusResponseViewModel<List<NotificationStorageCommand>>> response = handler.handleGetByResponsavel(cpfResponsavel);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<List<NotificationStorageCommand>> responseBody = response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertEquals("Erro ao buscar notificações: " + exceptionMessage, responseBody.getInfoMessage(), "O infoMessage no corpo deve corresponder à mensagem de erro");
        assertEquals("Error", responseBody.getStatusMessage(), "O statusMessage no corpo deve ser 'Error'");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        verify(notificationStorageService, times(1)).getNotificationsByResponsavel(cpfResponsavel);
        verify(mapperUtil, times(0)).map(any(NotificationStorage.class), eq(NotificationStorageCommand.class));
    }
}

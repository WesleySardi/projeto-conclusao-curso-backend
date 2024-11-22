package org.example.biomedbacktdd.handlers.notification;

import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.notification.NotificationStorage;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationStorageService;
import org.example.biomedbacktdd.util.MapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationStorageHandler {

    private final INotificationStorageService notificationStorageService;
    private final MapperUtil mapperUtil;

    public NotificationStorageHandler(INotificationStorageService notificationStorageService, MapperUtil mapperUtil) {
        this.notificationStorageService = notificationStorageService;
        this.mapperUtil = mapperUtil;
    }

    // Handle Create
    public ResponseEntity<StatusResponseViewModel> handleCreate(NotificationStorageCommand notificationStorageCommand) {
        try {
            NotificationStorage notification = notificationStorageService.storeNotification(notificationStorageCommand);

            StatusResponseViewModel response = new StatusResponseViewModel<>();
            response.setStatus(HttpStatus.OK.value());
            response.setIsOk(true);
            response.setInfoMessage("Notificação criada com sucesso.");
            response.setContentResponse(mapperUtil.map(notification, NotificationStorageCommand.class)); // Map para DTO, se necessário

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            StatusResponseViewModel errorResponse = new StatusResponseViewModel<>();
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setIsOk(false);
            errorResponse.setInfoMessage("Erro ao criar notificação: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Handle Delete
    public ResponseEntity<StatusResponseViewModel> handleDelete(Long id) {
        try {
            notificationStorageService.deleteNotification(id);

            StatusResponseViewModel response = new StatusResponseViewModel<>();
            response.setStatus(HttpStatus.OK.value());
            response.setIsOk(true);
            response.setInfoMessage("Notificação removida com sucesso.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            StatusResponseViewModel errorResponse = new StatusResponseViewModel<>();
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setIsOk(false);
            errorResponse.setInfoMessage("Erro ao remover notificação: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleGetByResponsavel(String cpfResponsavel) {
        try {
            List<NotificationStorage> notifications = notificationStorageService.getNotificationsByResponsavel(cpfResponsavel);

            // Mapear as entidades para DTOs
            List<NotificationStorageCommand> notificationDTOs = notifications.stream()
                    .map(notification -> {
                        NotificationStorageCommand command = mapperUtil.map(notification, NotificationStorageCommand.class);
                        // Populando manualmente o cpfResponsavel no DTO
                        command.setCpfResponsavel(notification.getResponsavel().getCpfRes());
                        return command;
                    })
                    .collect(Collectors.toList());

            // Criar resposta de sucesso
            StatusResponseViewModel response = new StatusResponseViewModel<>();
            response.setStatus(HttpStatus.OK.value());
            response.setIsOk(true);
            response.setInfoMessage("Notificações encontradas com sucesso.");
            response.setStatusMessage("Success");
            response.setContentResponse(notificationDTOs);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Capturar erros e retornar resposta de erro
            StatusResponseViewModel errorResponse = new StatusResponseViewModel<>();
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setIsOk(false);
            errorResponse.setInfoMessage("Erro ao buscar notificações: " + e.getMessage());
            errorResponse.setStatusMessage("Error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}

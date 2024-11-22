package org.example.biomedbacktdd.handlers.notification;

import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationFacadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationFacadeHandler {

    private final INotificationFacadeService notificationFacadeService;

    public NotificationFacadeHandler(INotificationFacadeService notificationFacadeService) {
        this.notificationFacadeService = notificationFacadeService;
    }

    public ResponseEntity<StatusResponseViewModel> handleSendAndStoreNotification(NotificationRequestCommand notificationRequestCommand) {
        try {
            notificationFacadeService.sendAndStoreNotification(notificationRequestCommand);

            StatusResponseViewModel response = new StatusResponseViewModel<>();
            response.setStatus(200);
            response.setIsOk(true);
            response.setInfoMessage("Notificação enviada e armazenada com sucesso.");
            response.setStatusMessage("Success");
            response.setContentResponse(notificationRequestCommand);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            StatusResponseViewModel errorResponse = new StatusResponseViewModel<>();
            errorResponse.setStatus(500);
            errorResponse.setIsOk(false);
            errorResponse.setInfoMessage("Erro ao enviar e armazenar notificação: " + e.getMessage());
            errorResponse.setStatusMessage("Error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}


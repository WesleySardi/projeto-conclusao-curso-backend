package org.example.biomedbacktdd.handlers.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationRequestHandler {

    private final INotificationRequestService notificationRequestService;

    public NotificationRequestHandler(INotificationRequestService notificationRequestService) {
        this.notificationRequestService = notificationRequestService;
    }

    public ResponseEntity<StatusResponseViewModel> handleSendNotification(NotificationRequest request) {
        try {
            String messageId = notificationRequestService.sendNotification(request);
            var response = new StatusResponseViewModel<>(messageId, "Notificação enviada com sucesso.", "Sucesso", HttpStatus.OK.value(), true);
            return ResponseEntity.ok(response);
        } catch (FirebaseMessagingException e) {
            var errorResponse = new StatusResponseViewModel<>(null, "Erro", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}


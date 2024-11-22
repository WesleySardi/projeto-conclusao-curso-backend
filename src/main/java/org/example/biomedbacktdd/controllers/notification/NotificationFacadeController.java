package org.example.biomedbacktdd.controllers.notification;

import jakarta.validation.Valid;
import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.notification.NotificationFacadeHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationFacadeController {

    private final NotificationFacadeHandler notificationFacadeHandler;

    public NotificationFacadeController(NotificationFacadeHandler notificationFacadeHandler) {
        this.notificationFacadeHandler = notificationFacadeHandler;
    }

    @PostMapping("/send-and-store")
    public ResponseEntity<StatusResponseViewModel> sendAndStoreNotification(@Valid @RequestBody NotificationRequestCommand notificationRequestCommand) {
        return notificationFacadeHandler.handleSendAndStoreNotification(notificationRequestCommand);
    }
}

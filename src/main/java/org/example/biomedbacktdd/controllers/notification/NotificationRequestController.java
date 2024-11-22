package org.example.biomedbacktdd.controllers.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.example.biomedbacktdd.handlers.notification.NotificationRequestHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationRequestController {

    private final NotificationRequestHandler notificationRequestHandler;

    public NotificationRequestController(NotificationRequestHandler notificationRequestHandler) {
        this.notificationRequestHandler = notificationRequestHandler;
    }

    @PostMapping("/send")
    @Operation(
            summary = "Send a Notification",
            description = "Sends a notification to a specified device token using Firebase Cloud Messaging.",
            tags = {"Notifications"},
            responses = {
                    @ApiResponse(
                            description = "Notification sent successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    ),
                    @ApiResponse(
                            description = "Error sending notification",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    )
            }
    )
    public ResponseEntity<StatusResponseViewModel> sendNotification(@RequestBody NotificationRequest request) {
        return notificationRequestHandler.handleSendNotification(request);
    }
}

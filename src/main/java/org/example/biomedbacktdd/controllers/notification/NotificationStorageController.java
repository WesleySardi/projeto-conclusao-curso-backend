package org.example.biomedbacktdd.controllers.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.notification.NotificationStorageHandler;
import org.example.biomedbacktdd.services.NotificationStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationStorageController {

    private final NotificationStorageHandler notificationStorageHandler;

    @Autowired
    public NotificationStorageController(NotificationStorageHandler notificationStorageHandler, NotificationStorageService notificationStorageService) {
        this.notificationStorageHandler = notificationStorageHandler;
    }

    @PostMapping
    @Operation(
            summary = "Create Notification",
            description = "Stores a new notification in the database.",
            tags = {"Notifications"},
            responses = {
                    @ApiResponse(
                            description = "Notification created successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    ),
                    @ApiResponse(
                            description = "Invalid request payload",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    )
            }
    )
    public ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> createNotification(@Valid @RequestBody NotificationStorageCommand notificationDTO) {
        return notificationStorageHandler.handleCreate(notificationDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Notification",
            description = "Deletes a notification from the database using its ID.",
            tags = {"Notifications"},
            responses = {
                    @ApiResponse(
                            description = "Notification deleted successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    ),
                    @ApiResponse(
                            description = "Notification not found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    )
            }
    )
    public ResponseEntity<StatusResponseViewModel<NotificationStorageCommand>> deleteNotification(@PathVariable int id) {
        return notificationStorageHandler.handleDelete(id);
    }

    @GetMapping("/responsavel/{cpfResponsavel}")
    @Operation(
            summary = "Get Notifications by Responsible's CPF",
            description = "Retrieves all notifications associated with the provided CPF of the responsible person.",
            tags = {"Notifications"},
            responses = {
                    @ApiResponse(
                            description = "Notifications retrieved successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    ),
                    @ApiResponse(
                            description = "No notifications found",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    )
            }
    )
    public ResponseEntity<StatusResponseViewModel<List<NotificationStorageCommand>>> getNotificationsByResponsavel(@PathVariable String cpfResponsavel) {
        return notificationStorageHandler.handleGetByResponsavel(cpfResponsavel);
    }
}

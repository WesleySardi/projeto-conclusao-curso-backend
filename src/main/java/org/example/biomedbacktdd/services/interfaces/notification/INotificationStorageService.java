package org.example.biomedbacktdd.services.interfaces.notification;


import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.entities.notification.NotificationStorage;

import java.util.List;

public interface INotificationStorageService {
    NotificationStorage storeNotification(NotificationStorageCommand notificationDTO);
    void deleteNotification(Long id);
    List<NotificationStorage> getNotificationsByResponsavel(String cpfResponsavel);
}

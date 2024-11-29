package org.example.biomedbacktdd.repositories.interfaces.notification;


import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.entities.notification.NotificationStorage;

import java.util.List;

public interface INotificationStorageService {
    NotificationStorage storeNotification(NotificationStorageCommand notificationStorageCommand);
    void deleteNotification(int idNotificacao);
    List<NotificationStorage> getNotificationsByResponsavel(String cpfResponsavel);
}

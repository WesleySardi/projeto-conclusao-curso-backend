package org.example.biomedbacktdd.repositories.interfaces.notification;

import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;

public interface INotificationFacadeService {
    void sendAndStoreNotification(NotificationRequestCommand notificationRequestCommand);
}


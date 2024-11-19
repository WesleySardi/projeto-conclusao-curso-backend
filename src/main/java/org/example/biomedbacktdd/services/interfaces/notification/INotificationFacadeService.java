package org.example.biomedbacktdd.services.interfaces.notification;

import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;

public interface INotificationFacadeService {
    void sendAndStoreNotification(NotificationRequestCommand notificationRequestCommand);
}


package org.example.biomedbacktdd.repositories.interfaces.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;

public interface INotificationRequestService {
    String sendNotification(NotificationRequest request) throws FirebaseMessagingException;
}

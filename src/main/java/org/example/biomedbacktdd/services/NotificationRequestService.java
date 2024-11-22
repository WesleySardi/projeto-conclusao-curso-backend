package org.example.biomedbacktdd.services;

import com.google.firebase.messaging.*;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationRequestService;
import org.springframework.stereotype.Service;

@Service
public class NotificationRequestService implements INotificationRequestService {

    private final FirebaseMessaging firebaseMessaging;

    public NotificationRequestService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(NotificationRequest request) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setToken(request.getToken())
                .putData("navigationId", "NotificationTab")
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setNotification(AndroidNotification.builder()
                                .setTitle(request.getTitle())
                                .setBody(request.getBody())
                                .setChannelId("zlo-app-notification-channel")
                                .build())
                        .build()
                )
                .build();

        // Send the message using FirebaseMessaging
        return firebaseMessaging.send(message);
    }
}
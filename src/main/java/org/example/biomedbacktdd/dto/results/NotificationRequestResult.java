package org.example.biomedbacktdd.dto.results;

/**
 * Result para encapsular os dados de resposta de uma solicitação de notificação.
 */
public class NotificationRequestResult {

    private String notificationId;
    private String statusMessage;

    // Construtor parametrizado
    public NotificationRequestResult(String notificationId, String statusMessage) {
        this.notificationId = notificationId;
        this.statusMessage = statusMessage;
    }

    // Getters e Setters
    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    // Método toString para facilitar a depuração
    @Override
    public String toString() {
        return "NotificationRequestResult{" +
                "notificationId='" + notificationId + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}


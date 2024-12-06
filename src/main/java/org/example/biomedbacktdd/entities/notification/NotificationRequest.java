package org.example.biomedbacktdd.entities.notification;

import java.util.Objects;

public class NotificationRequest {
    private String token;
    private String title;
    private String body;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationRequest that)) return false;
        return Objects.equals(getToken(), that.getToken()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getTitle(), getBody());
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "token='" + token + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}

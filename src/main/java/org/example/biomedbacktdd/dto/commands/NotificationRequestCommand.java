package org.example.biomedbacktdd.dto.commands;

public class NotificationRequestCommand {
    private String title;
    private String body;
    private String cpfResponsavel;

    // Construtor vazio
    public NotificationRequestCommand() {
    }

    // Construtor com parâmetros
    public NotificationRequestCommand(String title, String body, String cpfResponsavel) {
        this.title = title;
        this.body = body;
        this.cpfResponsavel = cpfResponsavel;
    }

    // Getters e Setters
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

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    // Método toString para depuração
    @Override
    public String toString() {
        return "NotificationRequestCommand{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", cpfResponsavel='" + cpfResponsavel + '\'' +
                '}';
    }
}

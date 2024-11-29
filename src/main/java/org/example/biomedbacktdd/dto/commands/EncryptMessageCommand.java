package org.example.biomedbacktdd.dto.commands;

public class EncryptMessageCommand {
    private String url;

    public EncryptMessageCommand(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package org.example.biomedbacktdd.DTO.commands;

public class DecryptMessageCommand {
    private String url;

    public DecryptMessageCommand() {
    }

    public DecryptMessageCommand(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

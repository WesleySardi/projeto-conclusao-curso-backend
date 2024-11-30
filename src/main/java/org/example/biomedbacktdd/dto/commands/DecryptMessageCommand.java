package org.example.biomedbacktdd.dto.commands;

public class DecryptMessageCommand {
    private String url;

    public DecryptMessageCommand(String url) {
        this.url = url;
    }

    public DecryptMessageCommand() { // EMPTY CONSTRUCTOR
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

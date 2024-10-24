package org.example.biomedbacktdd.DTO.commands;

public class EncryptRequestDTO {
    private String url;

    public EncryptRequestDTO() {
    }

    public EncryptRequestDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

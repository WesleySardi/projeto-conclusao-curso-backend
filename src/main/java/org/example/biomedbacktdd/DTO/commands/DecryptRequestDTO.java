package org.example.biomedbacktdd.DTO.commands;

public class DecryptRequestDTO {
    private String url;

    public DecryptRequestDTO() {
    }

    public DecryptRequestDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

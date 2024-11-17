package org.example.biomedbacktdd.DTO.results;

public class EncryptedMessageResult {
    private String encryptedUrl;
    private String message;

    public EncryptedMessageResult(String encryptedUrl, String message) {
        this.encryptedUrl = encryptedUrl;
        this.message = message;
    }

    // Getters and setters
    public String getEncryptedUrl() {
        return encryptedUrl;
    }

    public void setEncryptedUrl(String encryptedUrl) {
        this.encryptedUrl = encryptedUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

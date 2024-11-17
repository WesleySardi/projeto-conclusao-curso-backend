package org.example.biomedbacktdd.DTO.results;

public class DecryptedMessageResult {
    private String decryptedUrl;
    private String message;

    public DecryptedMessageResult(String decryptedUrl, String message) {
        this.decryptedUrl = decryptedUrl;
        this.message = message;
    }

    // Getters and setters
    public String getDecryptedUrl() {
        return decryptedUrl;
    }

    public void setDecryptedUrl(String decryptedUrl) {
        this.decryptedUrl = decryptedUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

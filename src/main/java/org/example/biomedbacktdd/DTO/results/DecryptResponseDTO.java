package org.example.biomedbacktdd.DTO.results;

public class DecryptResponseDTO {
    private String decryptedUrl;
    private String message;

    public DecryptResponseDTO(String encryptedUrl, String message) {
        this.decryptedUrl = encryptedUrl;
        this.message = message;
    }

    // Getters and setters
    public String getEncryptedUrl() {
        return decryptedUrl;
    }

    public void setEncryptedUrl(String encryptedUrl) {
        this.decryptedUrl = encryptedUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package org.example.biomedbacktdd.handlers.other;

import org.example.biomedbacktdd.DTO.results.DecryptResponseDTO;
import org.example.biomedbacktdd.DTO.results.EncryptResponseDTO;
import org.example.biomedbacktdd.services.interfaces.other.IOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OtherHandler {
    private final IOtherService otherService;

    @Autowired
    public OtherHandler(IOtherService otherService) {
        this.otherService = otherService;
    }

    public ResponseEntity<EncryptResponseDTO> handleEncryptUrl(String url) {
        try {
            String encryptedUrl = otherService.encryptUrl(url);
            EncryptResponseDTO response = new EncryptResponseDTO(encryptedUrl, "URL successfully encrypted");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            EncryptResponseDTO response = new EncryptResponseDTO(null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<DecryptResponseDTO> handleDecryptUrl(String url) {
        try {
            String decryptedUrl = otherService.decryptUrl(url);
            DecryptResponseDTO response = new DecryptResponseDTO(decryptedUrl, "URL successfully decrypted");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            DecryptResponseDTO response = new DecryptResponseDTO(null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

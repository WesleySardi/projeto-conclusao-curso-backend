package org.example.biomedbacktdd.services.interfaces.other;

import org.example.biomedbacktdd.DTO.results.DecryptResponseDTO;
import org.example.biomedbacktdd.DTO.results.EncryptResponseDTO;

public interface IOtherService {
    EncryptResponseDTO encryptUrl(String url);
    DecryptResponseDTO decryptUrl(String encryptedUrl);
}

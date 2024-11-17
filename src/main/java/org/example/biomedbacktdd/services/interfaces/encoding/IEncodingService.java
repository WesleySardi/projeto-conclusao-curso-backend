package org.example.biomedbacktdd.services.interfaces.encoding;

import org.example.biomedbacktdd.DTO.results.DecryptedMessageResult;
import org.example.biomedbacktdd.DTO.results.EncryptedMessageResult;

public interface IEncodingService {
    EncryptedMessageResult encryptUrl(String url);
    DecryptedMessageResult decryptUrl(String encryptedUrl);
}

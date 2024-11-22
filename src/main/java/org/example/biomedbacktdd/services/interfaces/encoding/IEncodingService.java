package org.example.biomedbacktdd.services.interfaces.encoding;

import org.example.biomedbacktdd.dto.results.DecryptedMessageResult;
import org.example.biomedbacktdd.dto.results.EncryptedMessageResult;

public interface IEncodingService {
    EncryptedMessageResult encryptUrl(String url);
    DecryptedMessageResult decryptUrl(String encryptedUrl);
}

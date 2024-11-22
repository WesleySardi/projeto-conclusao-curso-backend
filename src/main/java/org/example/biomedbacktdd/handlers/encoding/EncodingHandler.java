package org.example.biomedbacktdd.handlers.encoding;

import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.encoding.IEncodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EncodingHandler {
    private final IEncodingService encodingService;

    @Autowired
    public EncodingHandler(IEncodingService encodingService) {
        this.encodingService = encodingService;
    }

    public ResponseEntity<StatusResponseViewModel> handleEncryptUrl(String url) {
        StatusResponseViewModel errorResponse;

        try {
            var response = encodingService.encryptUrl(url);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Mensagem codificada com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao codificar a mensagem.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleDecryptUrl(String url) {
        StatusResponseViewModel errorResponse;

        try {
            var response = encodingService.decryptUrl(url);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Mensagem decodificada com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao decodificar a mensagem.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

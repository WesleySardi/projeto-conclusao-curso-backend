package org.example.biomedbacktdd.handlers.other;

import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
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

    public ResponseEntity<StatusResponseDTO> handleEncryptUrl(String url) {
        StatusResponseDTO errorResponse;

        try {
            var response =otherService.encryptUrl(url);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Mensagem codificada com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao codificar a mensagem.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleDecryptUrl(String url) {
        StatusResponseDTO errorResponse;

        try {
            var response = otherService.decryptUrl(url);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Mensagem codificada com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao decodificar a mensagem.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

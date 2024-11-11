package org.example.biomedbacktdd.handlers.mixed;

import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.services.interfaces.mixed.IMixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MixedHandler {

    private final IMixedService mixedService;

    @Autowired
    public MixedHandler(IMixedService mixedService) {
        this.mixedService = mixedService;
    }

    public ResponseEntity<StatusResponseDTO> handleFindByIdWithSecurity(String cpfDep, String emergePhone) {
        StatusResponseDTO errorResponse;

        try {
            var response = mixedService.findByIdWithSecurity(cpfDep, emergePhone);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro na busca.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindWebDataByIdWithSecurity(String cpfDep, String emergePhone) {
        StatusResponseDTO errorResponse;

        try {
            var response = mixedService.findWebDataByIdWithSecurity(cpfDep, emergePhone);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro na busca.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

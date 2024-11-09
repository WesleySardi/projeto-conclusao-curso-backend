package org.example.biomedbacktdd.handlers.dependent;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.services.interfaces.dependent.IDependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DependentHandler {

    private final IDependentService dependentService;

    @Autowired
    public DependentHandler(IDependentService dependentService) {
        this.dependentService = dependentService;
    }

    public ResponseEntity<StatusResponseDTO> handleFindAll(Pageable pageable) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindDependentsByName(String firstname, Pageable pageable) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findDependentsByName(firstname, pageable);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindDependentsByCpfRes(String cpfRes, Pageable pageable) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findDependentsByCpfRes(cpfRes, pageable);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindById(String id) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleVerifyDependentsCpfAndEmergPhone(String cpfDep, String emergPhone) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.verifyDependentsCpfAndEmergPhone(cpfDep, emergPhone);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleCreate(DependentDTO dependent) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.create(dependent);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleUpdate(DependentDTO dependent) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.update(dependent);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleDelete(String id) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.delete(id);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Autenticação concluída!", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Falha na autenticação!", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "An unexpected error occurred.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

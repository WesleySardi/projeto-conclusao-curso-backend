package org.example.biomedbacktdd.handlers.sms;

import org.example.biomedbacktdd.DTO.commands.SmsHandlerDTO;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.services.interfaces.sms.ISmsHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SmsHandlerHandler {

    private final ISmsHandlerService smsHandlerService;

    @Autowired
    public SmsHandlerHandler(ISmsHandlerService smsHandlerService) {
        this.smsHandlerService = smsHandlerService;
    }

    public ResponseEntity<StatusResponseDTO> handleFindAll(Pageable pageable) {
        StatusResponseDTO errorResponse;

        try {
            var response = smsHandlerService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "SMS encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao encontrar o SMS.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindById(Integer id) {
        StatusResponseDTO errorResponse;

        try {
            var response = smsHandlerService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "SMS encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao encontrar o SMS.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleCreate(SmsHandlerDTO sms) {
        StatusResponseDTO errorResponse;

        try {
            var response = smsHandlerService.create(sms);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "SMS criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao criar o SMS.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleUpdate(Integer smsCode, Timestamp returnDate) {
        StatusResponseDTO errorResponse;

        try {
            var response = smsHandlerService.update(smsCode, returnDate);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "SMS alterado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao alterar o SMS.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleVerifySmsCode(Integer smsCode, Timestamp returnDate, String cpfDep) {
        StatusResponseDTO errorResponse;

        try {
            var response = smsHandlerService.verifySmsCode(smsCode, returnDate, cpfDep);

            if (response != false) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Código SMS válido.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Código SMS inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleDelete(Integer id) {
        StatusResponseDTO errorResponse;

        try {
            var response = smsHandlerService.delete(id);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "SMS deletado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao deletar o SMS.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleDeleteByCpfDep(String cpfDep) {
        StatusResponseDTO errorResponse;

        try {
            var response = smsHandlerService.deleteByCpfDep(cpfDep);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "SMS deletado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao deletar o SMS.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

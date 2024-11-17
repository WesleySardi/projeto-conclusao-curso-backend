package org.example.biomedbacktdd.handlers.sms;

import org.example.biomedbacktdd.DTO.commands.NewSmsCommand;
import org.example.biomedbacktdd.DTO.viewmodels.StatusResponseViewModel;
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

    public ResponseEntity<StatusResponseViewModel> handleFindAll(Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = smsHandlerService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "SMS encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao encontrar o SMS.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleFindById(Integer id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = smsHandlerService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "SMS encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao encontrar o SMS.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleCreate(NewSmsCommand sms) {
        StatusResponseViewModel errorResponse;

        try {
            var response = smsHandlerService.create(sms);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "SMS criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao criar o SMS.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleUpdate(Integer smsCode, Timestamp returnDate) {
        StatusResponseViewModel errorResponse;

        try {
            var response = smsHandlerService.update(smsCode, returnDate);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "SMS alterado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao alterar o SMS.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleVerifySmsCode(Integer smsCode, Timestamp returnDate, String cpfDep) {
        StatusResponseViewModel errorResponse;

        try {
            var response = smsHandlerService.verifySmsCode(smsCode, returnDate, cpfDep);

            if (response != false) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Código SMS válido.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Código SMS inválido.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleDelete(Integer id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = smsHandlerService.delete(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "SMS deletado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao deletar o SMS.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleDeleteByCpfDep(String cpfDep) {
        StatusResponseViewModel errorResponse;

        try {
            var response = smsHandlerService.deleteByCpfDep(cpfDep);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "SMS deletado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao deletar o SMS.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

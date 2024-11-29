package org.example.biomedbacktdd.handlers.email;

import org.example.biomedbacktdd.dto.commands.NewEmailCommand;
import org.example.biomedbacktdd.dto.results.NewEmailResult;
import org.example.biomedbacktdd.dto.viewmodels.NewEmailViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.email.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailHandler {
    private static final String SUCESSO = "Sucesso";
    private static final String ERRO = "Erro";
    private static final String UNKNOWN_ERROR = "Um erro inesperado aconteceu.";
    private final IEmailService emailService;

    @Autowired
    public EmailHandler(IEmailService emailService) {
        this.emailService = emailService;
    }

    public ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewEmailViewModel>>>> handleFindAll(Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Emails encontrados com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao encontrar os emails.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<Boolean>> handleVerifyEmailCode(String email, int code) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.verifyEmailCode(email, code);

            if (response) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Código do email válido.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(false, ERRO, "Código do email inválido.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(false, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewEmailViewModel>> handleFindById(int id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Email encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao encontrar o email.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewEmailResult>> handleCreate(NewEmailCommand dependent) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.create(dependent);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Email criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao criar email.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<String>> handleSendQrCodeWithSendGrid(String toEmail) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.sendQrCodeWithSendGrid(toEmail);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "QR code enviado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao enviar o QR code.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

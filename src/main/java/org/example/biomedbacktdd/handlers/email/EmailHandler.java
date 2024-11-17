package org.example.biomedbacktdd.handlers.email;

import org.example.biomedbacktdd.DTO.commands.NewEmailCommand;
import org.example.biomedbacktdd.DTO.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.email.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailHandler {

    private final IEmailService emailService;

    @Autowired
    public EmailHandler(IEmailService emailService) {
        this.emailService = emailService;
    }

    public ResponseEntity<StatusResponseViewModel> handleFindAll(Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Emails encontrados com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao encontrar os emails.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleVerifyEmailCode(String email, int code) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.verifyEmailCode(email, code);

            if (response) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Código do email válido.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(false, "Erro", "Código do email inválido.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(false, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleFindById(int id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Email encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao encontrar o email.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleCreate(NewEmailCommand dependent) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.create(dependent);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Email criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao criar email.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleSendQrCodeWithSendGrid(String toEmail) {
        StatusResponseViewModel errorResponse;

        try {
            var response = emailService.sendQrCodeWithSendGrid(toEmail);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "QR code enviado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao enviar o QR code.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

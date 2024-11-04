package org.example.biomedbacktdd.handlers.email;

import org.example.biomedbacktdd.DTO.commands.EmailDTO;
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

    private final IEmailService emailService;

    @Autowired
    public EmailHandler(IEmailService emailService) {
        this.emailService = emailService;
    }

    public PagedModel<EntityModel<EmailDTO>> handleFindAll(Pageable pageable) {
        try {
            return emailService.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<?> handleVerifyEmailCode(String email, int code) {
        try {
            boolean isCodeValid = emailService.verifyEmailCode(email, code);
            if(isCodeValid) {
                return ResponseEntity.ok().body("Verification successful.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid code or email.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public EmailDTO handleFindById(int id) {
        try {
            return emailService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public EmailDTO handleCreate(EmailDTO dependent) {
        try {
            return emailService.create(dependent);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void handleSendQrCodeWithSendGrid(String toEmail) {
        try {
            emailService.sendQrCodeWithSendGrid(toEmail);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

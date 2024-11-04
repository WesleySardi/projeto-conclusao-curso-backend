package org.example.biomedbacktdd.services.interfaces.email;

import org.example.biomedbacktdd.DTO.commands.EmailDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface IEmailService {
    EmailDTO create(EmailDTO emailVO);

    PagedModel<EntityModel<EmailDTO>> findAll(Pageable pageable);

    EmailDTO findById(Integer id);
    void deleteByEmail(String emailUser);
    boolean verifyEmailCode(String email, int code);
    void sendQrCodeWithSendGrid(String toEmail);
}

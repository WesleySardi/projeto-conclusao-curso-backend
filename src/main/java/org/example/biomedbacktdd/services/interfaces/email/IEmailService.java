package org.example.biomedbacktdd.services.interfaces.email;

import org.example.biomedbacktdd.dto.commands.NewEmailCommand;
import org.example.biomedbacktdd.dto.results.NewEmailResult;
import org.example.biomedbacktdd.dto.viewmodels.NewEmailViewModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface IEmailService {
    NewEmailResult create(NewEmailCommand emailVO);
    PagedModel<EntityModel<NewEmailViewModel>> findAll(Pageable pageable);
    NewEmailViewModel findById(Integer id);
    String deleteByEmail(String emailUser);
    Boolean verifyEmailCode(String email, int code);
    String sendQrCodeWithSendGrid(String toEmail);
}

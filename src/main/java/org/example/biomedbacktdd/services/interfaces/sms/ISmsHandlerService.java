package org.example.biomedbacktdd.services.interfaces.sms;

import org.example.biomedbacktdd.dto.commands.NewSmsCommand;
import org.example.biomedbacktdd.dto.results.NewSmsResult;
import org.example.biomedbacktdd.dto.viewmodels.NewSmsViewModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.sql.Timestamp;

public interface ISmsHandlerService {
    PagedModel<EntityModel<NewSmsViewModel>> findAll(Pageable pageable);
    NewSmsViewModel findById(Integer id);
    NewSmsResult create(NewSmsCommand sms);
    NewSmsResult update(Integer smsCode, Timestamp returnDate);
    boolean verifySmsCode(Integer smsCode, Timestamp returnDate, String cpfDep);
    Integer delete(Integer id);
    String deleteByCpfDep(String cpfDep);
}

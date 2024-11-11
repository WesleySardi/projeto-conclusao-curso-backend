package org.example.biomedbacktdd.services.interfaces.sms;

import org.example.biomedbacktdd.DTO.commands.SmsHandlerDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.sql.Timestamp;

public interface ISmsHandlerService {
    PagedModel<EntityModel<SmsHandlerDTO>> findAll(Pageable pageable);
    SmsHandlerDTO findById(Integer id);
    SmsHandlerDTO create(SmsHandlerDTO sms);
    SmsHandlerDTO update(Integer smsCode, Timestamp returnDate);
    boolean verifySmsCode(Integer smsCode, Timestamp returnDate, String cpfDep);
    Integer delete(Integer id);
    String deleteByCpfDep(String cpfDep);
}

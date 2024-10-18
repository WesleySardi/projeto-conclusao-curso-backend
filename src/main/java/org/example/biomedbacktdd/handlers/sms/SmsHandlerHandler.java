package org.example.biomedbacktdd.handlers.sms;

import org.example.biomedbacktdd.DTO.commands.SmsHandlerDTO;
import org.example.biomedbacktdd.services.interfaces.sms.ISmsHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

    public PagedModel<EntityModel<SmsHandlerDTO>> handleFindAll(Pageable pageable) {
        try {
            return smsHandlerService.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public SmsHandlerDTO handleFindById(Integer id) {
        try {
            return smsHandlerService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public SmsHandlerDTO handleCreate(SmsHandlerDTO sms) {
        try {
            return smsHandlerService.create(sms);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public SmsHandlerDTO handleUpdate(Integer smsCode, Timestamp returnDate) {
        try {
            return smsHandlerService.update(smsCode, returnDate);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean handleVerifySmsCode(Integer smsCode, Timestamp returnDate, String cpfDep) {
        try {
            return smsHandlerService.verifySmsCode(smsCode, returnDate, cpfDep);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<String> handleDelete(Integer id) {
        try {
            smsHandlerService.delete(id);

            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> handleDeleteByCpfDep(String cpfDep) {
        try {
            smsHandlerService.deleteByCpfDep(cpfDep);

            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

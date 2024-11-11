package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.DTO.commands.SmsHandlerDTO;
import org.example.biomedbacktdd.controllers.sms.SmsHandlerController;
import org.example.biomedbacktdd.entities.sms.SmsHandler;
import org.example.biomedbacktdd.exceptions.RequiredObjectIsNullException;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.repositories.interfaces.sms.ISmsHandlerRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.example.biomedbacktdd.services.interfaces.sms.ISmsHandlerService;
import org.example.biomedbacktdd.sms.MessageSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SmsHandlerService implements ISmsHandlerService {
    private final Logger logger = Logger.getLogger(SmsHandlerService.class.getName());
    private final ISmsHandlerRepository repository;
    private final PagedResourcesAssembler<SmsHandlerDTO> assembler;

    @Autowired
    public SmsHandlerService(ISmsHandlerRepository repository,
                             PagedResourcesAssembler<SmsHandlerDTO> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public PagedModel<EntityModel<SmsHandlerDTO>> findAll(Pageable pageable) {
        PagedModel<EntityModel<SmsHandlerDTO>> response = null;

        try {
            logger.info("Finding all SMS!");

            var smsPage = repository.findAll(pageable);
            var smsVosPage = smsPage.map(p -> DozerMapper.parseObject(p, SmsHandlerDTO.class));
            smsVosPage.map(p -> p.add(linkTo(methodOn(SmsHandlerController.class).findById(p.getKey())).withSelfRel()));

            Link link = linkTo(methodOn(SmsHandlerController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
            response = assembler.toModel(smsVosPage, link);
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public SmsHandlerDTO findById(Integer id) {
        SmsHandlerDTO response = null;

        try {
            logger.info("Finding a SMS!");

            var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            var vo = DozerMapper.parseObject(entity, SmsHandlerDTO.class);

            vo.add(linkTo(methodOn(SmsHandlerController.class).findById(id)).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public SmsHandlerDTO create(SmsHandlerDTO sms) {
        SmsHandlerDTO response = null;

        try {
            if (sms == null) throw new RequiredObjectIsNullException();

            logger.info("Creating a SMS!");

            MessageSms smsMessage = new MessageSms();
            smsMessage.setCodigoSMS();

            sms.setKey(smsMessage.getCodigoSMS());

            try {
                var entity = DozerMapper.parseObject(sms, SmsHandler.class);
                var vo = DozerMapper.parseObject(repository.save(entity), SmsHandlerDTO.class);
                vo.add(linkTo(methodOn(SmsHandlerController.class).findById(vo.getKey())).withSelfRel());
                smsMessage.sendSms(sms.getPhoneUser());

                response = vo;
            } catch (Exception e) {
                response = null;
            }
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public SmsHandlerDTO update(Integer smsCode, Timestamp returnDate) {
        SmsHandlerDTO response = null;

        try {
            if (smsCode == null || returnDate == null) throw new RequiredObjectIsNullException();

            logger.info("Updating a SMS!");

            var entity = repository.findById(smsCode).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID - Update!"));

            entity.setReturnDate(returnDate);

            var vo = DozerMapper.parseObject(repository.save(entity), SmsHandlerDTO.class);

            vo.add(linkTo(methodOn(SmsHandlerController.class).findById(vo.getKey())).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public boolean verifySmsCode(Integer smsCode, Timestamp returnDate, String cpfDep) {
        boolean response = false;

        try {
            if (smsCode == null || cpfDep == null || returnDate == null) throw new RequiredObjectIsNullException();

            logger.info("Verifying a SMS!");

            var entity = repository.findById(smsCode).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID - Verify!"));

            if (entity.getCpfDep().equals(cpfDep)) {

                Timestamp timestamp1 = entity.getSendDate();
                Timestamp timestamp2 = returnDate;

                Instant instant1 = timestamp1.toInstant();
                Instant instant2 = timestamp2.toInstant().minus(Duration.ofHours(3));
                Duration duration = Duration.between(instant1, instant2);

                Timestamp timestamp = Timestamp.from(instant2);

                update(entity.getSmsCode(), timestamp);

                deleteByCpfDep(cpfDep);

                if (duration.getSeconds() < 600) {
                    response = true;
                } else {
                    response = false;
                }
            } else {
                response = false;
            }
        } catch (Exception e) {
            response = false;
        }

        return response;
    }

    public Integer delete(Integer id) {
        Integer response = null;

        try {
            logger.info("Deleting all SmsHandler with the correspondent cpfDep!");

            var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
            repository.delete(entity);
            response = id;
        } catch (Exception e) {
            response = null;
        }

        return response;
    }

    public String deleteByCpfDep(String cpfDep) {
        String response = null;

        try {
            logger.info("Deleting all SmsHandler with the correspondent cpfDep!");

            repository.deleteByCpfDep(cpfDep);
            response = cpfDep;
        } catch (Exception e) {
            response = null;
        }

        return response;
    }
}
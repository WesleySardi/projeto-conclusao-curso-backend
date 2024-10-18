package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.commands.DependentWebDataDTO;
import org.example.biomedbacktdd.controllers.dependent.DependentController;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.repositories.interfaces.dependent.IDependentRepository;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.example.biomedbacktdd.services.interfaces.mixed.IMixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MixedService implements IMixedService {
    private final Logger logger = Logger.getLogger(DependentService.class.getName());
    private final IDependentRepository depRepository;
    private final IResponsibleRepository resRepository;

    @Autowired
    public MixedService(IDependentRepository depRepository,
                        IResponsibleRepository resRepository) {
        this.depRepository = depRepository;
        this.resRepository = resRepository;
    }

    public DependentDTO findByIdWithSecurity(String cpfDep, String emergePhone) {
        Integer emergePhoneByCpf = Integer.valueOf(resRepository.findResponsibleEmergPhoneByCpfDep(cpfDep));
        Integer emergePhoneByPath = Integer.valueOf(emergePhone);
        if (emergePhoneByCpf.equals(emergePhoneByPath)) {
            logger.info("Finding a dependent!");

            var entity = depRepository.findById(cpfDep).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            var vo = DozerMapper.parseObject(entity, DependentDTO.class);

            vo.add(linkTo(methodOn(DependentController.class).findById(cpfDep)).withSelfRel());

            return vo;
        } else {
            throw new RuntimeException("The emergency phones " + emergePhoneByCpf + " and " + emergePhoneByPath + " aren't the same.");
        }
    }

    public DependentWebDataDTO findWebDataByIdWithSecurity(String cpfDep, String emergePhone) {
        String emergePhoneByCpf = resRepository.findResponsibleEmergPhoneByCpfDep(cpfDep);
        String emergePhoneByPath = emergePhone;
        if (emergePhoneByCpf.equals(emergePhoneByPath)) {
            logger.info("Finding a dependent!");

            var entity = depRepository.findById(cpfDep).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            var vo = DozerMapper.parseObject(entity, DependentWebDataDTO.class);

            vo.add(linkTo(methodOn(DependentController.class).findById(cpfDep)).withSelfRel());

            return vo;
        } else {
            throw new RuntimeException("The emergency phones " + emergePhoneByCpf + " and " + emergePhoneByPath + " aren't the same.");
        }
    }
}
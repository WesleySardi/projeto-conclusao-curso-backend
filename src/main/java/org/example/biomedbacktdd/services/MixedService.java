package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.commands.DependentWebDataDTO;
import org.example.biomedbacktdd.controllers.dependent.DependentController;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.repositories.DependentRepository;
import org.example.biomedbacktdd.repositories.ResponsibleRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MixedService {

    private Logger logger = Logger.getLogger(DependentService.class.getName());

    @Autowired
    DependentRepository depRepository;

    @Autowired
    ResponsibleRepository resRepository;

    @Autowired
    PagedResourcesAssembler<DependentDTO> assembler;

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
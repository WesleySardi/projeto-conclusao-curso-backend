package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.DTO.commands.ResponsibleDTO;
import org.example.biomedbacktdd.controllers.responsible.ResponsibleController;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.exceptions.RequiredObjectIsNullException;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.example.biomedbacktdd.services.interfaces.responsible.IResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ResponsibleService implements IResponsibleService {
    private final Logger logger = Logger.getLogger(ResponsibleService.class.getName());
    private final IResponsibleRepository repository;
    private final PagedResourcesAssembler<ResponsibleDTO> assembler;

    @Autowired
    public ResponsibleService(IResponsibleRepository repository,
                              PagedResourcesAssembler<ResponsibleDTO> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public PagedModel<EntityModel<ResponsibleDTO>> findAll(Pageable pageable) {
        logger.info("Finding all responsibles!");

        var responsiblePage = repository.findAll(pageable);
        var responsibleVosPage = responsiblePage.map(p -> DozerMapper.parseObject(p, ResponsibleDTO.class));
        responsibleVosPage.map(p -> p.add(linkTo(methodOn(ResponsibleController.class).findById(p.getKey())).withSelfRel()));

        Link link = linkTo(methodOn(ResponsibleController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(responsibleVosPage, link);
    }

    public PagedModel<EntityModel<ResponsibleDTO>> findResponsiblesByName(String firstname, Pageable pageable) {
        logger.info("Finding all people!");

        var responsiblePage = repository.findResponsiblesByName(firstname, pageable);

        var responsibleVosPage = responsiblePage.map(p -> DozerMapper.parseObject(p, ResponsibleDTO.class));
        responsibleVosPage.map(p -> p.add(linkTo(methodOn(ResponsibleController.class).findById(p.getKey())).withSelfRel()));

        Link link = linkTo(methodOn(ResponsibleController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(responsibleVosPage, link);
    }

    public List<Object[]> findResponsiblesCpfAndName(String emailRes, String senhaRes) {
        logger.info("Finding Responsible by E-mail and Password!");

        var responsiblePage = repository.findResponsiblesCpfAndName(emailRes, senhaRes);

        return responsiblePage;
    }

    public ResponsibleDTO findById(String id) {

        logger.info("Finding a responsible!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var vo = DozerMapper.parseObject(entity, ResponsibleDTO.class);

        vo.add(linkTo(methodOn(ResponsibleController.class).findById(id)).withSelfRel());

        return vo;
    }

    public ResponsibleDTO create(ResponsibleDTO responsible) {
        if (responsible == null) throw new RequiredObjectIsNullException();

        logger.info("Creating a responsible!");

        var entity = DozerMapper.parseObject(responsible, Responsible.class);

        var vo = DozerMapper.parseObject(repository.save(entity), ResponsibleDTO.class);

        vo.add(linkTo(methodOn(ResponsibleController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public ResponsibleDTO update(ResponsibleDTO responsible) {
        if (responsible == null) throw new RequiredObjectIsNullException();

        logger.info("Updating a responsible!");

        var entity = repository.findById(responsible.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setNomeRes(responsible.getNomeRes());
        entity.setEmailRes(responsible.getEmailRes());
        entity.setContato1Res(responsible.getContato1Res());
        entity.setContato2Res(responsible.getContato2Res());
        entity.setContato3Res(responsible.getContato3Res());
        entity.setIdadeRes(responsible.getIdadeRes());
        entity.setRgRes(responsible.getRgRes());
        entity.setPlanoAssinado(responsible.getPlanoAssinado());
        entity.setEnderecoIdRes(responsible.getEnderecoIdRes());

        var vo = DozerMapper.parseObject(repository.save(entity), ResponsibleDTO.class);

        vo.add(linkTo(methodOn(ResponsibleController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(String id) {
        logger.info("Deleting a Responsible!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}

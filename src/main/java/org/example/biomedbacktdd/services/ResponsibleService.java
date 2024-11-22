package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.results.NewResponsibleResult;
import org.example.biomedbacktdd.dto.viewmodels.NewResponsibleViewModel;
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
    private final PagedResourcesAssembler<NewResponsibleViewModel> assembler;

    @Autowired
    public ResponsibleService(IResponsibleRepository repository,
                              PagedResourcesAssembler<NewResponsibleViewModel> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public PagedModel<EntityModel<NewResponsibleViewModel>> findAll(Pageable pageable) {
        PagedModel<EntityModel<NewResponsibleViewModel>> response = null;

        try {
            logger.info("Finding all responsibles!");

            var responsiblePage = repository.findAll(pageable);
            var responsibleVosPage = responsiblePage.map(p -> DozerMapper.parseObject(p, NewResponsibleViewModel.class));
            responsibleVosPage.map(p -> p.add(linkTo(methodOn(ResponsibleController.class).findById(p.getKey())).withSelfRel()));

            Link link = linkTo(methodOn(ResponsibleController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
            response = assembler.toModel(responsibleVosPage, link);
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public List<Object[]> findResponsiblesCpfAndName(String emailRes, String senhaRes) {
        List<Object[]> response = null;

        try {
            logger.info("Finding Responsible by E-mail and Password!");

            var responsiblePage = repository.findResponsiblesCpfAndName(emailRes, senhaRes);

            response = responsiblePage;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewResponsibleViewModel findById(String id) {
        NewResponsibleViewModel response = null;

        try {
            logger.info("Finding a responsible!");

            var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            var vo = DozerMapper.parseObject(entity, NewResponsibleViewModel.class);

            vo.add(linkTo(methodOn(ResponsibleController.class).findById(id)).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewResponsibleResult create(NewResponsibleCommand responsible) {
        NewResponsibleResult response = null;

        try {
            if (responsible == null) throw new RequiredObjectIsNullException();

            logger.info("Creating a responsible!");

            var entity = DozerMapper.parseObject(responsible, Responsible.class);

            var vo = DozerMapper.parseObject(repository.save(entity), NewResponsibleResult.class);

            vo.add(linkTo(methodOn(ResponsibleController.class).findById(vo.getKey())).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewResponsibleResult update(NewResponsibleCommand responsible) {
        NewResponsibleResult response = null;

        try {
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

            var vo = DozerMapper.parseObject(repository.save(entity), NewResponsibleResult.class);

            vo.add(linkTo(methodOn(ResponsibleController.class).findById(vo.getKey())).withSelfRel());

            response = vo;

        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewResponsibleResult updatePassword(NewResponsibleCommand responsible) {
        NewResponsibleResult response = null;

        try {
            if (responsible == null) throw new RequiredObjectIsNullException();

            logger.info("Updating a responsible password!");

            var entity = repository.findById(responsible.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            entity.setSenhaRes(responsible.getSenhaRes());

            var vo = DozerMapper.parseObject(repository.save(entity), NewResponsibleResult.class);

            vo.add(linkTo(methodOn(ResponsibleController.class).findById(vo.getKey())).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewResponsibleViewModel findByEmail(String email) {
        NewResponsibleViewModel response = null;

        try {
            logger.info("Finding Responsible by Email!");

            Responsible result = repository.findResponsibleByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found for this e-mail!"));

            response = DozerMapper.parseObject(result, NewResponsibleViewModel.class);
        } catch (Exception e) {
            return null;
        }

        return response;
    }
}

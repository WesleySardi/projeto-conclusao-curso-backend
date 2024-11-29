package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.controllers.dependent.DependentController;
import org.example.biomedbacktdd.dto.commands.NewDependentCommand;
import org.example.biomedbacktdd.dto.results.NewDependentResult;
import org.example.biomedbacktdd.dto.viewmodels.DependentNameViewModel;
import org.example.biomedbacktdd.dto.viewmodels.NewDependentViewModel;
import org.example.biomedbacktdd.entities.dependent.Dependent;
import org.example.biomedbacktdd.exceptions.RequiredObjectIsNullException;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.repositories.interfaces.dependent.IDependentRepository;
import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.example.biomedbacktdd.services.interfaces.dependent.IDependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DependentService implements IDependentService {

    private final Logger logger = Logger.getLogger(DependentService.class.getName());
    private final IDependentRepository repository;
    private final PagedResourcesAssembler<NewDependentViewModel> assembler;

    @Autowired
    public DependentService(IDependentRepository repository,
                            PagedResourcesAssembler<NewDependentViewModel> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public PagedModel<EntityModel<NewDependentViewModel>> findAll(Pageable pageable) {
        PagedModel<EntityModel<NewDependentViewModel>> response = null;

        try {
            logger.info("Finding all dependents!");

            var dependentPage = repository.findAll(pageable);
            var dependentVosPage = dependentPage.map(p -> DozerMapper.parseObject(p, NewDependentViewModel.class));
            dependentVosPage.map(p -> p.add(linkTo(methodOn(DependentController.class).findById(p.getKey())).withSelfRel()));

            Link link = linkTo(methodOn(DependentController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
            response = assembler.toModel(dependentVosPage, link);
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public PagedModel<EntityModel<NewDependentViewModel>> findDependentsByCpfRes(String cpfRes, Pageable pageable) {
        PagedModel<EntityModel<NewDependentViewModel>> response = null;

        try {
            logger.info("Finding all people by CpfRes!");

            var dependentPage = repository.findDependentsByCpfRes(cpfRes, pageable);

            var dependentVosPage = dependentPage.map(p -> DozerMapper.parseObject(p, NewDependentViewModel.class));
            dependentVosPage.map(p -> p.add(linkTo(methodOn(DependentController.class).findById(p.getKey())).withSelfRel()));

            Link link = linkTo(methodOn(DependentController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
            response = assembler.toModel(dependentVosPage, link);
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewDependentViewModel findById(String id) {
        NewDependentViewModel response = null;

        try {
            logger.info("Finding a dependent!");

            var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            var vo = DozerMapper.parseObject(entity, NewDependentViewModel.class);

            vo.add(linkTo(methodOn(DependentController.class).findById(id)).withSelfRel());

            response = vo;

        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewDependentResult create(NewDependentCommand dependent) {
        NewDependentResult response = null;

        try {
            if (dependent == null) throw new RequiredObjectIsNullException();

            logger.info("Creating a dependent!");

            var entity = DozerMapper.parseObject(dependent, Dependent.class);

            var vo = DozerMapper.parseObject(repository.save(entity), NewDependentResult.class);

            vo.add(linkTo(methodOn(DependentController.class).findById(vo.getKey())).withSelfRel());

            response = vo;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public NewDependentResult update(NewDependentCommand dependent) {
        NewDependentResult response = null;

        try {
            if (dependent == null) throw new RequiredObjectIsNullException();

            logger.info("Updating a dependent!");

            var entity = repository.findById(dependent.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            entity.setNomeDep(dependent.getNomeDep());
            entity.setIdadeDep(dependent.getIdadeDep());
            entity.setTipoSanguineo(dependent.getTipoSanguineo());
            entity.setLaudo(dependent.getLaudo());
            entity.setGeneroDep(dependent.getGeneroDep());
            entity.setRgDep(dependent.getRgDep());
            entity.setCpfResDep(dependent.getCpfResDep());
            entity.setPiTagIdDep(dependent.getPiTagIdDep());
            entity.setCpfTerDep(dependent.getCpfTerDep());
            entity.setIdCirurgiaDep(dependent.getIdCirurgiaDep());
            entity.setIdScanDep(dependent.getIdScanDep());

            var vo = DozerMapper.parseObject(repository.save(entity), NewDependentResult.class);

            vo.add(linkTo(methodOn(DependentController.class).findById(vo.getKey())).withSelfRel());

            response = vo;

        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public String delete(String id) {
        String response = null;

        try {
            logger.info("Deleting a Dependent!");

            var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            repository.delete(entity);

            response = id;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public DependentNameViewModel getDependentNameByCpf(String cpfDep) {
        String nomeDep = repository.findDependentNameByCpf(cpfDep);
        if (nomeDep == null) {
            throw new ResourceNotFoundException("Dependente não encontrado para o CPF: " + cpfDep);
        }
        return new DependentNameViewModel(nomeDep);
    }
}

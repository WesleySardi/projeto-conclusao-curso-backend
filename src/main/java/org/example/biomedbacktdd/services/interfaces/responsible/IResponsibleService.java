package org.example.biomedbacktdd.services.interfaces.responsible;

import org.example.biomedbacktdd.DTO.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.DTO.results.NewResponsibleResult;
import org.example.biomedbacktdd.DTO.viewmodels.NewResponsibleViewModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface IResponsibleService {
    PagedModel<EntityModel<NewResponsibleViewModel>> findAll(Pageable pageable);
    List<Object[]> findResponsiblesCpfAndName(String emailRes, String senhaRes);
    NewResponsibleViewModel findById(String id);
    NewResponsibleResult create(NewResponsibleCommand responsible);
    NewResponsibleResult update(NewResponsibleCommand responsible);
    NewResponsibleResult updatePassword(NewResponsibleCommand responsible);
    NewResponsibleViewModel findByEmail(String email);
}

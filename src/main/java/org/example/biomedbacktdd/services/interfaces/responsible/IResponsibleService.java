package org.example.biomedbacktdd.services.interfaces.responsible;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.results.NewResponsibleResult;
import org.example.biomedbacktdd.dto.viewmodels.NewResponsibleViewModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface IResponsibleService {
    PagedModel<EntityModel<NewResponsibleViewModel>> findAll(Pageable pageable);
    NewResponsibleViewModel findById(String id);
    NewResponsibleResult create(NewResponsibleCommand responsible);
    NewResponsibleResult update(NewResponsibleCommand responsible);
    NewResponsibleViewModel findByEmail(String email);
}

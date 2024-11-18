package org.example.biomedbacktdd.services.interfaces.dependent;

import org.example.biomedbacktdd.dto.commands.NewDependentCommand;
import org.example.biomedbacktdd.dto.results.NewDependentResult;
import org.example.biomedbacktdd.dto.viewmodels.NewDependentViewModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface IDependentService {
    PagedModel<EntityModel<NewDependentViewModel>> findAll(Pageable pageable);
    PagedModel<EntityModel<NewDependentViewModel>> findDependentsByCpfRes(String cpfRes, Pageable pageable);
    NewDependentViewModel findById(String id);
    NewDependentResult create(NewDependentCommand dependent);
    NewDependentResult update(NewDependentCommand dependent);
    String delete(String id);
}

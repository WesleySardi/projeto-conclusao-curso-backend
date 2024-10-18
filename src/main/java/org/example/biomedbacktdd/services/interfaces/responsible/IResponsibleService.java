package org.example.biomedbacktdd.services.interfaces.responsible;

import org.example.biomedbacktdd.DTO.commands.ResponsibleDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface IResponsibleService {
    PagedModel<EntityModel<ResponsibleDTO>> findAll(Pageable pageable);
    PagedModel<EntityModel<ResponsibleDTO>> findResponsiblesByName(String firstname, Pageable pageable);
    List<Object[]> findResponsiblesCpfAndName(String emailRes, String senhaRes);
    ResponsibleDTO findById(String id);
    ResponsibleDTO create(ResponsibleDTO responsible);
    ResponsibleDTO update(ResponsibleDTO responsible);
    void delete(String id);
}

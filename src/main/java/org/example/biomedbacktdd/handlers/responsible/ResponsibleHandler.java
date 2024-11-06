package org.example.biomedbacktdd.handlers.responsible;

import org.example.biomedbacktdd.DTO.commands.ResponsibleDTO;
import org.example.biomedbacktdd.services.interfaces.responsible.IResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibleHandler {

    private final IResponsibleService responsibleService;

    @Autowired
    public ResponsibleHandler(IResponsibleService responsibleService) {
        this.responsibleService = responsibleService;
    }

    public PagedModel<EntityModel<ResponsibleDTO>> handleFindAll(Pageable pageable) {
        try {
            return responsibleService.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PagedModel<EntityModel<ResponsibleDTO>> handleFindResponsiblesByName(String firstname, Pageable pageable) {
        try {
            return responsibleService.findResponsiblesByName(firstname, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Object[]> handleFindResponsiblesCpfAndName(String emailRes, String senhaRes) {
        try {
            return responsibleService.findResponsiblesCpfAndName(emailRes, senhaRes);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponsibleDTO handleFindById(String id) {
        try {
            return responsibleService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponsibleDTO handleCreate(ResponsibleDTO responsible) {
        try {
            return responsibleService.create(responsible);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponsibleDTO handleUpdate(ResponsibleDTO responsible) {
        try {
            return responsibleService.update(responsible);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<String> handleDelete(String id) {
        try {
            responsibleService.delete(id);

            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponsibleDTO handleUpdatePassword(ResponsibleDTO responsibleDTO) {
        try {
            return responsibleService.updatePassword(responsibleDTO);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponsibleDTO handleFindByTelefone(String id) {
        try {
            return responsibleService.findByTelefone(id);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponsibleDTO handleFindByEmail(String id) {
        try {
            return responsibleService.findByEmail(id);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

package org.example.biomedbacktdd.handlers.dependent;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.services.interfaces.dependent.IDependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DependentHandler {

    private final IDependentService dependentService;

    @Autowired
    public DependentHandler(IDependentService dependentService) {
        this.dependentService = dependentService;
    }

    public PagedModel<EntityModel<DependentDTO>> handleFindAll(Pageable pageable) {
        try {
            return dependentService.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PagedModel<EntityModel<DependentDTO>> handleFindDependentsByName(String firstname, Pageable pageable) {
        try {
            return dependentService.findDependentsByName(firstname, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PagedModel<EntityModel<DependentDTO>> handleFindDependentsByCpfRes(String cpfRes, Pageable pageable) {
        try {
            return dependentService.findDependentsByCpfRes(cpfRes, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DependentDTO handleFindById(String id) {
        try {
            return dependentService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Map<String, String> handleVerifyDependentsCpfAndEmergPhone(String cpfDep, String emergPhone) {
        try {
            return dependentService.verifyDependentsCpfAndEmergPhone(cpfDep, emergPhone);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DependentDTO handleCreate(DependentDTO dependent) {
        try {
            return dependentService.create(dependent);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DependentDTO handleUpdate(DependentDTO dependent) {
        try {
            return dependentService.update(dependent);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<String> handleDelete(String id) {
        try {
            dependentService.delete(id);

            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

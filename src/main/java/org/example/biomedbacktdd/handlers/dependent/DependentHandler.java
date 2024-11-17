package org.example.biomedbacktdd.handlers.dependent;

import org.example.biomedbacktdd.DTO.commands.NewDependentCommand;
import org.example.biomedbacktdd.DTO.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.dependent.IDependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DependentHandler {

    private final IDependentService dependentService;

    @Autowired
    public DependentHandler(IDependentService dependentService) {
        this.dependentService = dependentService;
    }

    public ResponseEntity<StatusResponseViewModel> handleFindAll(Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Dependentes encontrados com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Nenhum dependente encontrado.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleFindDependentsByCpfRes(String cpfRes, Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.findDependentsByCpfRes(cpfRes, pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Dependente encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Nenhum dependente encontrado.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleFindById(String id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Dependente encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Nenhum dependente encontrado.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleCreate(NewDependentCommand dependent) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.create(dependent);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Dependente criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao criar dependente.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleUpdate(NewDependentCommand dependent) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.update(dependent);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Dependente alterado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao alterar dependente.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleDelete(String id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.delete(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Dependente deletado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao deletar dependente.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

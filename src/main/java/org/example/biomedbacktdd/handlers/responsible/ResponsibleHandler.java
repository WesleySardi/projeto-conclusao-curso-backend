package org.example.biomedbacktdd.handlers.responsible;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.results.NewResponsibleResult;
import org.example.biomedbacktdd.dto.viewmodels.NewResponsibleViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.responsible.IResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponsibleHandler {
    private static final String SUCESSO = "Sucesso";
    private static final String ERRO = "Erro";
    private static final String UNKNOWN_ERROR = "Um erro inesperado aconteceu.";
    private static final String RESPONSIBLE_FOUND_SUCCESS = "Responsavel encontrado com sucesso.";
    private static final String RESPONSIBLE_NOT_FOUND = "Erro ao encontrar o responsavel.";

    private final IResponsibleService responsibleService;

    @Autowired
    public ResponsibleHandler(IResponsibleService responsibleService) {
        this.responsibleService = responsibleService;
    }

    public ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewResponsibleViewModel>>>> handleFindAll(Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Responsáveis encontrados com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao encontrar os responsáveis.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewResponsibleViewModel>> handleFindById(String id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, RESPONSIBLE_FOUND_SUCCESS, HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, RESPONSIBLE_NOT_FOUND, HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewResponsibleResult>> handleCreate(NewResponsibleCommand responsible) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.create(responsible);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Responsavel criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao criar o responsavel.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewResponsibleResult>> handleUpdate(NewResponsibleCommand responsible) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.update(responsible);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Responsavel alterado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao alterar o responsavel.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewResponsibleViewModel>> handleFindByEmail(String id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.findByEmail(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, RESPONSIBLE_FOUND_SUCCESS, HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, RESPONSIBLE_NOT_FOUND, HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewResponsibleViewModel>> handleFindByTelefone(String telefone) {
        StatusResponseViewModel<NewResponsibleViewModel> errorResponse;

        try {
            var response = responsibleService.findByTelefone(telefone);

            if (response != null) {
                errorResponse = new StatusResponseViewModel<>(response, SUCESSO, RESPONSIBLE_FOUND_SUCCESS, HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel<>(null, ERRO, RESPONSIBLE_NOT_FOUND, HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel<>(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

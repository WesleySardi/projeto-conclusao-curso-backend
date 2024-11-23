package org.example.biomedbacktdd.handlers.responsible;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.responsible.IResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponsibleHandler {

    private final IResponsibleService responsibleService;

    @Autowired
    public ResponsibleHandler(IResponsibleService responsibleService) {
        this.responsibleService = responsibleService;
    }

    public ResponseEntity<StatusResponseViewModel> handleFindAll(Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Responsáveis encontrados com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao encontrar os responsávei.", HttpStatus.BAD_REQUEST.value(), false);
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
            var response = responsibleService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Responsavel encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao encontrar o responsavel.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleCreate(NewResponsibleCommand responsible) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.create(responsible);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Responsavel criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao criar o responsavel.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleUpdate(NewResponsibleCommand responsible) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.update(responsible);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Responsavel alterado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao alterar o responsavel.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleFindByEmail(String id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = responsibleService.findByEmail(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, "Sucesso", "Responsavel encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, "Erro", "Erro ao encontrar o responsavel.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

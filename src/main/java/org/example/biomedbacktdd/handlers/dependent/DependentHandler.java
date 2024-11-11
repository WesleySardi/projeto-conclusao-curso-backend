package org.example.biomedbacktdd.handlers.dependent;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
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

    public ResponseEntity<StatusResponseDTO> handleFindAll(Pageable pageable) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dependentes encontrados com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Nenhum dependente encontrado.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindDependentsByName(String firstname, Pageable pageable) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findDependentsByName(firstname, pageable);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dependente encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Nenhum dependente encontrado.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindDependentsByCpfRes(String cpfRes, Pageable pageable) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findDependentsByCpfRes(cpfRes, pageable);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dependente encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Nenhum dependente encontrado.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleFindById(String id) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dependente encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Nenhum dependente encontrado.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleVerifyDependentsCpfAndEmergPhone(String cpfDep, String emergPhone) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.verifyDependentsCpfAndEmergPhone(cpfDep, emergPhone);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dados válidos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Cpf e emergPhone não possuem relação.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleCreate(DependentDTO dependent) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.create(dependent);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dependente criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao criar dependente.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleUpdate(DependentDTO dependent) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.update(dependent);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dependente alterado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao alterar dependente.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleDelete(String id) {
        StatusResponseDTO errorResponse;

        try {
            var response = dependentService.delete(id);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dependente deletado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao deletar dependente.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }
}

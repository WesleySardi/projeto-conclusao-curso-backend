package org.example.biomedbacktdd.handlers.dependent;

import org.example.biomedbacktdd.dto.commands.NewDependentCommand;
import org.example.biomedbacktdd.dto.results.NewDependentResult;
import org.example.biomedbacktdd.dto.viewmodels.DependentNameViewModel;
import org.example.biomedbacktdd.dto.viewmodels.NewDependentViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.exceptions.ResourceNotFoundException;
import org.example.biomedbacktdd.services.interfaces.dependent.IDependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DependentHandler {

    private static final String SUCESSO = "Sucesso";
    private static final String ERRO = "Erro";
    private static final String ERRO_INTERNO = "Erro interno";
    private static final String NO_DEPENDENT = "Nenhum dependente encontrado.";
    private static final String UNKNOWN_ERROR = "Um erro inesperado aconteceu.";

    private final IDependentService dependentService;

    @Autowired
    public DependentHandler(IDependentService dependentService) {
        this.dependentService = dependentService;
    }

    public ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewDependentViewModel>>>> handleFindAll(Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.findAll(pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Dependentes encontrados com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, NO_DEPENDENT, HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewDependentViewModel>>>> handleFindDependentsByCpfRes(String cpfRes, Pageable pageable) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.findDependentsByCpfRes(cpfRes, pageable);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Dependente encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, NO_DEPENDENT, HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewDependentViewModel>> handleFindById(String id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.findById(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Dependente encontrado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, NO_DEPENDENT, HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewDependentResult>> handleCreate(NewDependentCommand dependent) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.create(dependent);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Dependente criado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao criar dependente.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<NewDependentResult>> handleUpdate(NewDependentCommand dependent) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.update(dependent);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Dependente alterado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao alterar dependente.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<String>> handleDelete(String id) {
        StatusResponseViewModel errorResponse;

        try {
            var response = dependentService.delete(id);

            if (response != null) {
                errorResponse = new StatusResponseViewModel(response, SUCESSO, "Dependente deletado com sucesso.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseViewModel(null, ERRO, "Erro ao deletar dependente.", HttpStatus.BAD_REQUEST.value(), false);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseViewModel(null, UNKNOWN_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<DependentNameViewModel>> handleFindDependentNameByCpfDep(String cpfDep) {
        StatusResponseViewModel<DependentNameViewModel> statusResponse;

        try {
            DependentNameViewModel nomeDep = dependentService.getDependentNameByCpf(cpfDep);

            if (nomeDep != null) {
                statusResponse = new StatusResponseViewModel<>(
                        nomeDep,
                        SUCESSO,
                        "Nome do dependente encontrado com sucesso.",
                        HttpStatus.OK.value(),
                        true
                );
                return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
            } else {
                statusResponse = new StatusResponseViewModel<>(
                        null,
                        ERRO,
                        "Nenhum dependente encontrado para o CPF fornecido.",
                        HttpStatus.BAD_REQUEST.value(),
                        false
                );
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(statusResponse);
            }
        } catch (ResourceNotFoundException ex) {
            statusResponse = new StatusResponseViewModel<>(
                    null,
                    "Não Encontrado",
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(statusResponse);
        } catch (Exception e) {
            statusResponse = new StatusResponseViewModel<>(
                    null,
                    ERRO_INTERNO,
                    "Um erro inesperado aconteceu: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusResponse);
        }
    }
}

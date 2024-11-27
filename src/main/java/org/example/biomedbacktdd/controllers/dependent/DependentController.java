package org.example.biomedbacktdd.controllers.dependent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.dto.commands.NewDependentCommand;
import org.example.biomedbacktdd.dto.results.NewDependentResult;
import org.example.biomedbacktdd.dto.viewmodels.NewDependentViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.dependent.DependentHandler;
import org.example.biomedbacktdd.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dependent")
@Tag(name = "Dependent", description = "Endpoints para lidar com Dependentes.")
public class DependentController {

    private final DependentHandler handler;

    @Autowired
    public DependentController(DependentHandler handler) {
        this.handler = handler;
    }

    @GetMapping(
            value = "/commonuser/findAll",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all Dependents", description = "Finds all Dependents",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = NewDependentCommand.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewDependentViewModel>>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var response = handler.handleFindAll(PageRequest.of(page, size, Sort.by("desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, "nomeDep")));

        return response;
    }

    @GetMapping(
            value = "/commonuser/findDependentsByCpfRes/{cpfRes}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds Dependents by CpfRes", description = "Finds Dependents by CpfRes",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = NewDependentCommand.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewDependentViewModel>>>> findDependentsByCpfRes(
            @PathVariable(value = "cpfRes") String cpfRes,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var response = handler.handleFindDependentsByCpfRes(cpfRes, PageRequest.of(page, size, Sort.by("desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, "nomeDep")));

        return response;
    }

    @GetMapping(
            value = "/commonuser/findById/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Finds a Dependent", description = "Finds a Dependent",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = NewDependentCommand.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<NewDependentViewModel>> findById(@PathVariable(value = "id") String id) {
        var response = handler.handleFindById(id);

        return response;
    }

    @PostMapping(
            value = "/commonuser/create",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Dependent", description = "Adds a new Dependent by passing in a JSON, XML or YML representation of the Dependent!",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = NewDependentCommand.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<NewDependentResult>> create(@RequestBody NewDependentCommand dependentDTO) {
        var response = handler.handleCreate(dependentDTO);

        return response;
    }

    @PutMapping(
            value = "/commonuser/update",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Dependent", description = "Updates a Dependent by passing in a JSON, XML or YML representation of the Dependent!",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = NewDependentCommand.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<NewDependentResult>> update(@RequestBody NewDependentCommand dependentVO) {
        var response = handler.handleUpdate(dependentVO);

        return response;
    }

    @DeleteMapping(value = "/commonuser/delete/{id}")
    @Operation(summary = "Deletes a Dependent", description = "Deletes a Dependent by passing in a JSON, XML or YML representation of the Dependent!",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<String>> delete(@PathVariable(value = "id") String id) {
        var response = handler.handleDelete(id);

        return response;
    }
}
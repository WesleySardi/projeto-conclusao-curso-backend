package org.example.biomedbacktdd.controllers.responsible;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.results.NewResponsibleResult;
import org.example.biomedbacktdd.dto.viewmodels.NewResponsibleViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.responsible.ResponsibleHandler;
import org.example.biomedbacktdd.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/responsible")
@Tag(name = "Responsible", description = "Endpoints para lidar com Responsáveis.")
public class ResponsibleController {

    @Autowired
    private final ResponsibleHandler handler;

    @Autowired
    public ResponsibleController(ResponsibleHandler handler) {
        this.handler = handler;
    }

    @GetMapping(
            value = "/commonuser/findAll",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all Responsible", description = "Finds all Responsible",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = NewResponsibleCommand.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewResponsibleViewModel>>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var response = handler.handleFindAll(PageRequest.of(page, size, Sort.by("desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, "nomeRes")));

        return response;
    }

    @GetMapping(
            value = "/commonuser/findById/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Finds a Responsible", description = "Finds a Responsible",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = NewResponsibleCommand.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<NewResponsibleViewModel>> findById(@PathVariable(value = "id") String id) {
        var response = handler.handleFindById(id);

        return response;
    }

    @PostMapping(
            value = "/create",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Responsible", description = "Adds a new Responsible by passing in a JSON, XML or YML representation of the Responsible!",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = NewResponsibleCommand.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<NewResponsibleResult>> create(@RequestBody NewResponsibleCommand responsibleDTO) {
        var response = handler.handleCreate(responsibleDTO);

        return response;
    }

    @PutMapping(
            value = "/commonuser/update",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Responsible", description = "Updates a Responsible by passing in a JSON, XML or YML representation of the Responsible!",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = NewResponsibleCommand.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<NewResponsibleResult>> update(@RequestBody NewResponsibleCommand responsibleDTO) {
        var response = handler.handleUpdate(responsibleDTO);

        return response;
    }

    @GetMapping(value = "/commonuser/findByEmail/{email}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds Responsible by Email", description = "Finds Responsible by his Email",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = NewResponsibleCommand.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<NewResponsibleViewModel>> findByEmail(@PathVariable(value = "email") String emailRes) {
        var response = handler.handleFindByEmail(emailRes);

        return response;
    }
}

package org.example.biomedbacktdd.controllers.responsible;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.DTO.commands.ResponsibleDTO;
import org.example.biomedbacktdd.handlers.responsible.ResponsibleHandler;
import org.example.biomedbacktdd.services.ResponsibleService;
import org.example.biomedbacktdd.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/responsible")
@Tag(name = "Responsible", description = "Endpoints para lidar com Responsáveis.")
public class ResponsibleController {

    private Logger logger = Logger.getLogger(ResponsibleService.class.getName());

    @Autowired
    private final ResponsibleHandler handler;

    @Autowired
    public ResponsibleController(ResponsibleHandler handler) {
        this.handler = handler;
    }

    @Operation(summary = "Saudação simples", description = "Retorna uma saudação simples 'Hello, World!'")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
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
                                            array = @ArraySchema(schema = @Schema(implementation = ResponsibleDTO.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PagedModel<EntityModel<ResponsibleDTO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "nomeRes"));

        return ResponseEntity.ok(handler.handleFindAll(pageable));
    }

    @GetMapping(
            value = "/commonuser/findResponsibleByName/{nomeRes}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds Responsible by Name", description = "Finds Responsible by Name",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ResponsibleDTO.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PagedModel<EntityModel<ResponsibleDTO>>> findResponsibleByName(
            @PathVariable(value = "nomeRes") String nomeRes,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "nomeRes"));

        return ResponseEntity.ok(handler.handleFindResponsiblesByName(nomeRes, pageable));
    }

    @GetMapping(
            value = "/commonuser/findResponsibleCpfAndName/params",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds Responsible by Email and Password", description = "Finds Responsible by Email and Password",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ResponsibleDTO.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public List<Object[]> findResponsibleCpfAndName(
            @RequestParam(value = "emailRes") String emailRes,
            @RequestParam(value = "senhaRes") String senhaRes
    ) {
        return handler.handleFindResponsiblesCpfAndName(emailRes, senhaRes);
    }

    @GetMapping(
            value = "/commonuser/findById/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Finds a Responsible", description = "Finds a Responsible",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponsibleDTO findById(@PathVariable(value = "id") String id) {
        return handler.handleFindById(id);
    }

    @PostMapping(
            value = "/commonuser/create",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Responsible", description = "Adds a new Responsible by passing in a JSON, XML or YML representation of the Responsible!",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponsibleDTO create(@RequestBody ResponsibleDTO responsibleVO) {
        return handler.handleCreate(responsibleVO);
    }

    @PutMapping(
            value = "/commonuser/update",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Responsible", description = "Updates a Responsible by passing in a JSON, XML or YML representation of the Responsible!",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponsibleDTO update(@RequestBody ResponsibleDTO responsibleVO) {
        return handler.handleUpdate(responsibleVO);
    }

    @DeleteMapping(value = "/commonuser/delete/{id}")
    @Operation(summary = "Deletes a Responsible", description = "Deletes a Responsible by passing in a JSON, XML or YML representation of the Responsible!",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        handler.handleDelete(id);

        return ResponseEntity.noContent().build();
    }
}

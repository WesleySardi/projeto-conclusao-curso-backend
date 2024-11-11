package org.example.biomedbacktdd.controllers.responsible;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.DTO.commands.ResponsibleDTO;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.handlers.responsible.ResponsibleHandler;
import org.example.biomedbacktdd.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                                            array = @ArraySchema(schema = @Schema(implementation = ResponsibleDTO.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var response = handler.handleFindAll(PageRequest.of(page, size, Sort.by("desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, "nomeRes")));

        return response;
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
    public ResponseEntity<StatusResponseDTO> findResponsibleByName(
            @PathVariable(value = "nomeRes") String nomeRes,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var response = handler.handleFindResponsiblesByName(nomeRes, PageRequest.of(page, size, Sort.by("desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, "nomeRes")));

        return response;
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
    public ResponseEntity<StatusResponseDTO> findResponsibleCpfAndName(
            @RequestParam(value = "emailRes") String emailRes,
            @RequestParam(value = "senhaRes") String senhaRes
    ) {
        var response = handler.handleFindResponsiblesCpfAndName(emailRes, senhaRes);

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
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> findById(@PathVariable(value = "id") String id) {
        var response = handler.handleFindById(id);

        return response;
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
    public ResponseEntity<StatusResponseDTO> create(@RequestBody ResponsibleDTO responsibleVO) {
        var response = handler.handleCreate(responsibleVO);

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
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> update(@RequestBody ResponsibleDTO responsibleVO) {
        var response = handler.handleUpdate(responsibleVO);

        return response;
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
    public ResponseEntity<StatusResponseDTO> delete(@PathVariable(value = "id") String id) {
        var response = handler.handleDelete(id);

        return response;
    }

    @PutMapping(
            value = "/updatePassword",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Responsible", description = "Updates a Responsible by passing in a JSON, XML or YML representation of the Responsible!",
            tags = {"Responsibles"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> updatePassword(@RequestBody ResponsibleDTO responsibleVO) {
        var response = handler.handleUpdatePassword(responsibleVO);

        return response;
    }

    @GetMapping(value = "/findByTelefone/{telefone}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds Responsible by Telephone", description = "Finds Responsible by any of their Telephone numbers",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> findByTelefone(@PathVariable(value = "telefone") String telefone) {
        var response = handler.handleFindByTelefone(telefone);

        return response;
    }


    @GetMapping(value = "/findByEmail/{email}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds Responsible by Email", description = "Finds Responsible by his Email",
            tags = {"Responsible"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponsibleDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> findByEmail(@PathVariable(value = "email") String emailRes) {
        var response = handler.handleFindByEmail(emailRes);

        return response;
    }
}

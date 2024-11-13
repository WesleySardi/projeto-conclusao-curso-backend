package org.example.biomedbacktdd.controllers.dependent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.commands.SmsHandlerDTO;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.handlers.dependent.DependentHandler;
import org.example.biomedbacktdd.services.DependentService;
import org.example.biomedbacktdd.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/dependent")
@Tag(name = "Dependent", description = "Endpoints para lidar com Dependentes.")
public class DependentController {

    private Logger logger = Logger.getLogger(DependentService.class.getName());

    @Autowired
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
                                            array = @ArraySchema(schema = @Schema(implementation = DependentDTO.class)
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
        var response = handler.handleFindAll(PageRequest.of(page, size, Sort.by("desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, "nomeDep")));

        return response;
    }

    @GetMapping(
            value = "/commonuser/findDependentsByName/{nomeDep}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds Dependents by Name", description = "Finds Dependents by Name",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = DependentDTO.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> findDependentsByName(
            @PathVariable(value = "nomeDep") String nomeDep,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var response = handler.handleFindDependentsByName(nomeDep, PageRequest.of(page, size, Sort.by("desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, "nomeDep")));

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
                                            array = @ArraySchema(schema = @Schema(implementation = DependentDTO.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> findDependentsByCpfRes(
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
                            content = @Content(schema = @Schema(implementation = DependentDTO.class))
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

    @GetMapping(
            value = "/commonuser/verifyDependentsCPFandEmergPhone/params",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Finds a CPF and Emergency Phone", description = "Finds a CPF and Emergency Phone",
            tags = {"Dependent"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SmsHandlerDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> verifyDependentsCpfAndEmergPhone(@RequestParam(value = "cpfDep") String cpfDep, @RequestParam(value = "emergPhone") String emergPhone) {
        var response = handler.handleVerifyDependentsCpfAndEmergPhone(cpfDep, emergPhone);

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
                            content = @Content(schema = @Schema(implementation = DependentDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> create(@RequestBody DependentDTO dependentDTO) {
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
                            content = @Content(schema = @Schema(implementation = DependentDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseDTO> update(@RequestBody DependentDTO dependentVO) {
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
    public ResponseEntity<StatusResponseDTO> delete(@PathVariable(value = "id") String id) {
        var response = handler.handleDelete(id);

        return response;
    }
}
package org.example.biomedbacktdd.controllers.sms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.DTO.commands.SmsHandlerDTO;
import org.example.biomedbacktdd.handlers.dependent.DependentHandler;
import org.example.biomedbacktdd.handlers.sms.SmsHandlerHandler;
import org.example.biomedbacktdd.services.SmsHandlerService;
import org.example.biomedbacktdd.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/smshandler/")
@Tag(name = "SMS", description = "Endpoints for Managing SMS")
public class SmsHandlerController {

    private Logger logger = Logger.getLogger(SmsHandlerService.class.getName());

    @Autowired
    private final SmsHandlerHandler handler;

    @Autowired
    public SmsHandlerController(SmsHandlerHandler handler) {
        this.handler = handler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all SmsHandlers", description = "Finds all SmsHandlers",
            tags = {"SmsHandlers"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = SmsHandlerDTO.class)
                                            )
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PagedModel<EntityModel<SmsHandlerDTO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "smsCode"));

        return ResponseEntity.ok(handler.handleFindAll(pageable));
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Finds a SmsHandler", description = "Finds a SmsHandler",
            tags = {"SmsHandler"},
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
    public SmsHandlerDTO findById(@PathVariable(value = "id") Integer id) {
        return handler.handleFindById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new SmsHandler", description = "Adds a new SmsHandler by passing in a JSON, XML or YML representation of the SmsHandler!",
            tags = {"SmsHandlers"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SmsHandlerDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public SmsHandlerDTO create(@RequestBody SmsHandlerDTO smsHandlerVO) {
        return handler.handleCreate(smsHandlerVO);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a SmsHandler", description = "Updates a SmsHandler by passing in a JSON, XML or YML representation of the SmsHandler!",
            tags = {"SmsHandlers"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SmsHandlerDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public SmsHandlerDTO update(@RequestParam(value = "smsCode") Integer smsCode, @RequestParam(value = "returnDate") Timestamp returnDate) {
        return handler.handleUpdate(smsCode, returnDate);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a SmsHandler", description = "Deletes a SmsHandler by passing in a JSON, XML or YML representation of the SmsHandler!",
            tags = {"SmsHandlers"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
        handler.handleDelete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/verifySmsCode")
    @Operation(summary = "Verifies a SmsHandler", description = "Verifies a SmsHandler by passing in a JSON, XML or YML representation of the SmsHandler!",
            tags = {"SmsHandlers"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public boolean verifySmsCode(@RequestParam(value = "smsCode") Integer smsCode, @RequestParam(value = "returnDate") Timestamp returnDate, @RequestParam(value = "cpfDep") String cpfDep) {
        return handler.handleVerifySmsCode(smsCode, returnDate, cpfDep);
    }
}

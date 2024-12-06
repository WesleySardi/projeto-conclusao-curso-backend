package org.example.biomedbacktdd.controllers.devicestorage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.results.DeviceStorageResult;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.devicestorage.DeviceStorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devicestorage")
public class DeviceStorageController {

    @Autowired
    private DeviceStorageHandler handler;

    @GetMapping("/{cpfDep}")
    @Operation(summary = "Finds devices by dependent CPF", description = "Finds devices associated with a dependent's CPF",
            tags = {"Devices"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<List<DeviceStorageResult>>> findDispositivosByCpfDep(@PathVariable(value = "cpfDep") String cpfDep) {
        return handler.handleFindDispositivosByCpfDep(cpfDep);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Adds a new Device", description = "Adds a new Device by passing in a JSON, XML, or YML representation of the Device!",
            tags = {"Devices"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StatusResponseViewModel.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StatusResponseViewModel<DeviceStorageResult>> create(@Valid @RequestBody DeviceStorageCommand deviceStorageCommand) {
        return handler.handleCreate(deviceStorageCommand);
    }

}

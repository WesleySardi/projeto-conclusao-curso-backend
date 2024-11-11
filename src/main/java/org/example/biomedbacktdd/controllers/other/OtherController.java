package org.example.biomedbacktdd.controllers.other;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.DTO.commands.DecryptRequestDTO;
import org.example.biomedbacktdd.DTO.commands.EncryptRequestDTO;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.handlers.other.OtherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
@Tag(name = "Encrypt", description = "Endpoints para lidar com a Criptografia.")
public class OtherController {

    private final OtherHandler handler;

    @Autowired
    public OtherController(OtherHandler handler) {
        this.handler = handler;
    }

    @Operation(summary = "Encrypt an url")
    @PostMapping(value = "/encrypt")
    public ResponseEntity<StatusResponseDTO>encryptUrl(@RequestBody EncryptRequestDTO request) {
        var response = handler.handleEncryptUrl(request.getUrl());

        return response;
    }

    @Operation(summary = "Decrypt an url")
    @PostMapping(value = "/decrypt")
    public ResponseEntity<StatusResponseDTO> decryptUrl(@RequestBody DecryptRequestDTO request) {
        var response = handler.handleDecryptUrl(request.getUrl());

        return response;
    }
}

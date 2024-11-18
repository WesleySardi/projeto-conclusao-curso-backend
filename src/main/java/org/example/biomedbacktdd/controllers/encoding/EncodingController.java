package org.example.biomedbacktdd.controllers.encoding;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.dto.commands.DecryptMessageCommand;
import org.example.biomedbacktdd.dto.commands.EncryptMessageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.encoding.EncodingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
@Tag(name = "Encrypt", description = "Endpoints para lidar com a Criptografia.")
public class EncodingController {

    private final EncodingHandler handler;

    @Autowired
    public EncodingController(EncodingHandler handler) {
        this.handler = handler;
    }

    @Operation(summary = "Encrypt an url")
    @PostMapping(value = "/encrypt")
    public ResponseEntity<StatusResponseViewModel>encryptUrl(@RequestBody EncryptMessageCommand request) {
        var response = handler.handleEncryptUrl(request.getUrl());

        return response;
    }

    @Operation(summary = "Decrypt an url")
    @PostMapping(value = "/decrypt")
    public ResponseEntity<StatusResponseViewModel> decryptUrl(@RequestBody DecryptMessageCommand request) {
        var response = handler.handleDecryptUrl(request.getUrl());

        return response;
    }
}

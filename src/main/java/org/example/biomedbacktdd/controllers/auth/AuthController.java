package org.example.biomedbacktdd.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.VO.auth.AccountCredentialsVO;
import org.example.biomedbacktdd.entities.auth.User;
import org.example.biomedbacktdd.handlers.auth.AuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authorization", description = "Endpoints para lidar com a Autorização.")
public class AuthController {

    private final AuthHandler handler;

    @Autowired
    public AuthController(AuthHandler handler) {
        this.handler = handler;
    }

    @Operation(summary = "Register a user")
    @PostMapping(value = "/register")
    public ResponseEntity<StatusResponseDTO> register(@RequestBody User newUser) {
        var response = handler.handleRegister(newUser);

        return response;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity<StatusResponseDTO> signin(@RequestBody AccountCredentialsVO data) {
        var response = handler.handleSignin(data);

        return response;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refreshToken/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
        var response = handler.handleRefreshToken(username, refreshToken);

        return response;

    }
}

package org.example.biomedbacktdd.handlers.auth;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.biomedbacktdd.DTO.results.StatusResponseDTO;
import org.example.biomedbacktdd.VO.auth.AccountCredentialsVO;
import org.example.biomedbacktdd.entities.auth.User;
import org.example.biomedbacktdd.services.interfaces.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthHandler {

    private final IAuthService authService;

    @Autowired
    public AuthHandler(IAuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<StatusResponseDTO> handleRegister(@RequestBody User newUser) {
        StatusResponseDTO errorResponse;

        try {
            var response = authService.register(newUser);

           if (response != null) {
               errorResponse = new StatusResponseDTO(response, "Sucesso", "Usuário registrado com sucesso.", HttpStatus.OK.value(), true);
               return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
           } else {
               errorResponse = new StatusResponseDTO(null, "Erro", "Erro ao registrar o usuário.", HttpStatus.NOT_FOUND.value(), false);
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
           }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleSignin(AccountCredentialsVO data) {
        StatusResponseDTO errorResponse;

        try {
            if (checkIfParamsIsNotNull(data)) {
                errorResponse = new StatusResponseDTO(null, "Erro", "Usuário ou senha estão vazios.", HttpStatus.UNAUTHORIZED.value(), false);

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }

            var response = authService.signin(data);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Usuário ou senha inválidos.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseDTO> handleRefreshToken(String username, String refreshToken) {
        StatusResponseDTO errorResponse;

        try {
            if (checkIfParamsIsNotNull(username, refreshToken)) {
                errorResponse = new StatusResponseDTO(null, "Erro", "Usuário ou senha estão vazios.", HttpStatus.UNAUTHORIZED.value(), false);

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }

            var response = authService.refreshToken(username, refreshToken);

            if (response != null) {
                errorResponse = new StatusResponseDTO(response, "Sucesso", "Dados validos.", HttpStatus.OK.value(), true);
                return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
            } else {
                errorResponse = new StatusResponseDTO(null, "Erro", "Usuário inválido.", HttpStatus.UNAUTHORIZED.value(), false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            errorResponse = new StatusResponseDTO(null, "Um erro inesperado aconteceu.", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() && username == null || username.isBlank();
    }
}

package org.example.biomedbacktdd.handlers.auth;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.biomedbacktdd.VO.auth.AccountCredentialsVO;
import org.example.biomedbacktdd.entities.auth.User;
import org.example.biomedbacktdd.services.interfaces.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthHandler {

    private final IAuthService authService;

    @Autowired
    public AuthHandler(IAuthService authService) {
        this.authService = authService;
    }

    public User handleRegister(@RequestBody User newUser) {
        try {
            return authService.register(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity handleSignin(AccountCredentialsVO data) {
        try {
            return authService.signin(data);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity handleRefreshToken(String username, String refreshToken) {
        try {
            return authService.refreshToken(username, refreshToken);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

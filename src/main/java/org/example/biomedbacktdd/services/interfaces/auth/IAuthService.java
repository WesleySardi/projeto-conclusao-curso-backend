package org.example.biomedbacktdd.services.interfaces.auth;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.biomedbacktdd.VO.auth.AccountCredentialsVO;
import org.example.biomedbacktdd.entities.auth.User;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    User register(@RequestBody User newUser);
    ResponseEntity signin(AccountCredentialsVO data);
    ResponseEntity refreshToken(String username, String refreshToken);
}

package org.example.biomedbacktdd.services.interfaces.auth;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.biomedbacktdd.VO.auth.AccountCredentialsVO;
import org.example.biomedbacktdd.VO.auth.TokenVO;
import org.example.biomedbacktdd.entities.auth.User;

public interface IAuthService {
    User register(@RequestBody User newUser);
    TokenVO signin(AccountCredentialsVO data);
    TokenVO refreshToken(String username, String refreshToken);
}

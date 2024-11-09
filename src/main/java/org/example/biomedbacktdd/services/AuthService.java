package org.example.biomedbacktdd.services;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.biomedbacktdd.VO.auth.AccountCredentialsVO;
import org.example.biomedbacktdd.VO.auth.TokenVO;
import org.example.biomedbacktdd.entities.auth.User;
import org.example.biomedbacktdd.repositories.interfaces.auth.IUserRepository;
import org.example.biomedbacktdd.security.jwt.JwtTokenProvider;
import org.example.biomedbacktdd.services.interfaces.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private IUserRepository repository;

    public User register(@RequestBody User newUser) {
        User response = null;

        try {
            repository.save(newUser);

            response = newUser;
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    @SuppressWarnings("rawtypes")
    public TokenVO signin(AccountCredentialsVO data) {
        TokenVO response = null;

        try {
            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            if (user != null) {
                response = tokenProvider.createAccessToken(username, user.getRoles());
            }
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    @SuppressWarnings("rawtypes")
    public TokenVO refreshToken(String username, String refreshToken) {
        TokenVO response = null;

        try {
            var user = repository.findByUsername(username);

            if (user != null) {
                response = tokenProvider.refreshToken(refreshToken);
            }
        } catch (Exception e) {
            return null;
        }

        return response;
    }
}

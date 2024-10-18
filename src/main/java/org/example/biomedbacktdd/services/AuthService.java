package org.example.biomedbacktdd.services;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.biomedbacktdd.VO.auth.AccountCredentialsVO;
import org.example.biomedbacktdd.VO.auth.TokenVO;
import org.example.biomedbacktdd.entities.auth.User;
import org.example.biomedbacktdd.repositories.interfaces.auth.IUserRepository;
import org.example.biomedbacktdd.security.jwt.JwtTokenProvider;
import org.example.biomedbacktdd.services.interfaces.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        repository.save(newUser);

        return newUser;
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);

        var tokenResponse = new TokenVO();

        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }

        return ResponseEntity.ok(tokenResponse);
    }
}

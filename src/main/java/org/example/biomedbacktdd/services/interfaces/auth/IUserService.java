package org.example.biomedbacktdd.services.interfaces.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    UserDetails loadUserByUsername(String username);
}

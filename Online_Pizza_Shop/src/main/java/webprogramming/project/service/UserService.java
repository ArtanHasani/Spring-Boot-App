package webprogramming.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import webprogramming.project.model.Role;
import webprogramming.project.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String confirmPassword, String address, String ccn, Role role);
}

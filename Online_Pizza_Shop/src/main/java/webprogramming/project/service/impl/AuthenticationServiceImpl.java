package webprogramming.project.service.impl;

import org.springframework.stereotype.Service;
import webprogramming.project.model.User;
import webprogramming.project.model.exceptions.InvalidArgumentsException;
import webprogramming.project.model.exceptions.InvalidUserCredentialsException;
import webprogramming.project.repository.UserRepository;
import webprogramming.project.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}

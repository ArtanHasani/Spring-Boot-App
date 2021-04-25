package webprogramming.project.service;

import webprogramming.project.model.User;

public interface AuthenticationService {
    User login(String username, String password);
}

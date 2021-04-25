package webprogramming.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webprogramming.project.model.User;
import webprogramming.project.model.exceptions.InvalidUserCredentialsException;
import webprogramming.project.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","loginPage");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.authenticationService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception) {
            return "loginPage";
        }
    }
}


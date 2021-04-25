package webprogramming.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.User;
import webprogramming.project.model.exceptions.PizzaNotFoundException;
import webprogramming.project.service.OrderService;
import webprogramming.project.service.PizzaService;
import webprogramming.project.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/menu"})
public class MenuController {


    private final PizzaService pizzaService;

    public MenuController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public String getMenuPage(HttpServletRequest req, Model model){
        model.addAttribute("pizzas",this.pizzaService.findAll());
        model.addAttribute("bodyContent","menuPage");
        return "master-template";
    }



}

package webprogramming.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webprogramming.project.service.IngredientsService;
import webprogramming.project.service.PizzaService;

@Controller
@RequestMapping( value = {"/custom"})
public class CustomPizzaController {

    private final IngredientsService ingredientsService;

    public CustomPizzaController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public String getCustomPizzaPage(Model model){
        model.addAttribute("ingredients", this.ingredientsService.findAll());
        model.addAttribute("bodyContent","customPizzaPage");
        return "master-template";
    }
}

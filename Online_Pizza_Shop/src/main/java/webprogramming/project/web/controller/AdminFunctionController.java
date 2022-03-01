package webprogramming.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Manufacturer;
import webprogramming.project.service.IngredientsService;
import webprogramming.project.service.ManufacturerService;
import webprogramming.project.service.PizzaService;

import java.util.List;

@Controller
@RequestMapping(value = {"/adminFunctions"})
public class AdminFunctionController {

    private final IngredientsService ingredientsService;
    private final ManufacturerService manufacturerService;
    private final PizzaService pizzaService;

    public AdminFunctionController(IngredientsService ingredientsService, ManufacturerService manufacturerService, PizzaService pizzaService) {

        this.ingredientsService = ingredientsService;
        this.manufacturerService = manufacturerService;
        this.pizzaService = pizzaService;
    }


    @GetMapping
    public String getAdminFunctionsPage(Model model){
        model.addAttribute("bodyContent", "adminHomePage");
        return "master-template";
    }

    @GetMapping("/addNewPizza")
    public String getNewPizzaPage(Model model){
        List<Ingredients> ingredients = this.ingredientsService.findAll();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("bodyContent","addNewPizzaPage");
        return "master-template";
    }
    @PostMapping("/addNewPizza")
    public String savePizza(@RequestParam String newPizzaName,
                            @RequestParam List<Long> ingredientsList,
                            @RequestParam Double newPizzaCost,
                            @RequestParam String newPizzaSize,
                            @RequestParam String newPizzaUrl){
        this.pizzaService.save(newPizzaName, newPizzaSize, newPizzaCost, newPizzaUrl, ingredientsList);
        return "redirect:/adminFunctions/addNewPizza";
    }

    @GetMapping("/addNewPizza/addIngredient")
    public String getAddIngredientPage(Model model){
        List<Manufacturer> manufacturerList = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturerList);
        model.addAttribute("bodyContent","addNewIngredientPage");
        return "master-template";
    }

    @PostMapping("/addNewPizza/addIngredient")
    public String saveIngredient(@RequestParam String newIngredientName,
                                 @RequestParam Double newIngredientPrice,
                                 @RequestParam Long manId){

        if(manId != null){
            this.ingredientsService.save(newIngredientName, newIngredientPrice, manId);
        }
        return "redirect:/adminFunctions/addNewPizza";
    }

    @GetMapping("/addNewPizza/addIngredient/addManufacturer")
    public String getAddManufacturerPage(Model model){
        model.addAttribute("bodyContent","addNewManufacturerPage");
        return "master-template";
    }

    @PostMapping("/addNewPizza/addIngredient/addManufacturer")
    public String saveIngredient(@RequestParam String newManufacturerName,
                                 @RequestParam String newManufacturerCO){

       this.manufacturerService.save(newManufacturerName, newManufacturerCO);
        return "redirect:/adminFunctions/addNewPizza/addIngredient";
    }
}

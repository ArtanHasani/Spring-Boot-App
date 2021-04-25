package webprogramming.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.User;
import webprogramming.project.model.exceptions.IngredientIDInvalid;
import webprogramming.project.model.exceptions.PizzaNotFoundException;
import webprogramming.project.service.IngredientsService;
import webprogramming.project.service.OrderService;
import webprogramming.project.service.PizzaService;
import webprogramming.project.service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/order"})
public class OrderController {

    private final PizzaService pizzaService;
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(PizzaService pizzaService, UserService userService,OrderService orderService) {
        this.pizzaService = pizzaService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public String orderPage(HttpServletRequest req,
                            Model model){
        String username=req.getRemoteUser();
        User user = (User) this.userService.loadUserByUsername(username);
        Order order = this.orderService.getActiveOrder(user.getUsername());
        model.addAttribute("user",this.userService.loadUserByUsername(username));
        model.addAttribute("order", this.orderService.getActiveOrder(username));
        model.addAttribute("pizzas", this.orderService.getActiveOrder(username).getPizza());
        model.addAttribute("bodyContent", "orderPage");
        return "master-template";
    }

    @PostMapping("/add/{id}")
    public String addPizzaToOrder(@PathVariable Long id, HttpServletRequest req) {
        try {
            User user = (User) this.userService.loadUserByUsername(req.getRemoteUser());
            this.orderService.addPizza(user.getUsername(), id);
            return "redirect:/order";
        } catch (RuntimeException exception) {
            return "redirect:/order?error=" + exception.getMessage();
        }
    }

    @PostMapping("/add-custom")
    public String customPizza(@RequestParam(value = "ingredientIds", required = false) List<Long> ingredients,
                              @RequestParam(value = "pizzaSize", required = false) String pizzaSize,
                              @RequestParam(value = "customPizzaName", required = false) String pizzaName,
                              HttpServletRequest req,
                              Model model){

        double cost = 0.0;
        String username=req.getRemoteUser();
        User user = (User) this.userService.loadUserByUsername(username);
        cost = this.pizzaService.calculateCost(ingredients, pizzaSize);
        String customPizzaUrl = "https://mk0pieologyc5tbrq0kb.kinstacdn.com/wp-content/uploads/2020/11/Screenshot_SIDES__SWEETS_%E2%80%93_pieology.com_-_Google_Chrome_247.png";
        this.pizzaService.save(pizzaName, pizzaSize, cost, customPizzaUrl, ingredients);
        Pizza pizza = this.pizzaService.getPizzaFromName(pizzaName).orElseThrow(PizzaNotFoundException::new);
        addPizzaToOrder(pizza.getId(), req);
        return "redirect:/order";
    }

    @PostMapping("/make-order")
    public String makeOrder(@RequestParam(value = "pizzaIds", required = false)  List<Long> pizzaIds,
                            @RequestParam(value = "delivery", required = false) String delivery,
                           HttpServletRequest req,
                           Model model){

        String username=req.getRemoteUser();
        User user = (User) this.userService.loadUserByUsername(username);
        Order order = this.orderService.getActiveOrder(user.getUsername());
        double totalCost = 0.0;
        totalCost = this.orderService.calculateTotalCost(pizzaIds, delivery, order);

        model.addAttribute("totalCost",totalCost);
        model.addAttribute("order", order);
        model.addAttribute("bodyContent", "confirmOrderPage");
        return "master-template";
    }



    @GetMapping("/confirm")
    public String confirmOrder(@RequestParam(value = "cost", required = false) Double cost,
                               @RequestParam(value = "timeUntilPizzaArrives", required = false) String timeUntilPizzaArrives,
                               HttpServletRequest req){
        User user = (User) this.userService.loadUserByUsername(req.getRemoteUser());
        Order order = this.orderService.getActiveOrder(user.getUsername());
        order.setCost(cost);
        order.setTimeUntilPizzaArrives(timeUntilPizzaArrives);
        this.orderService.save(order);
        //order = new Order(user);
        //user.order.add(order);
        return "redirect:/home";
    }
}


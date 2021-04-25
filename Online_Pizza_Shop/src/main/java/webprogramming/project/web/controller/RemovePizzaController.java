package webprogramming.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.User;
import webprogramming.project.model.exceptions.PizzaNotFoundException;
import webprogramming.project.service.OrderService;
import webprogramming.project.service.PizzaService;
import webprogramming.project.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/remove"})
public class RemovePizzaController {

    private final UserService userService;
    private final OrderService orderService;
    private final PizzaService pizzaService;


    public RemovePizzaController(UserService userService, OrderService orderService, PizzaService pizzaService) {
        this.userService = userService;
        this.orderService = orderService;
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public String getRemovePizzaPage(HttpServletRequest req,
                                     Model model){
        model.addAttribute("pizzas", this.orderService.getActiveOrder(req.getRemoteUser()).getPizza());
        model.addAttribute("bodyContent", "removePizzaFromOrderPage");
        return "master-template";
    }

    @PostMapping("/{id}")
    public String removePizza(@PathVariable Long id, HttpServletRequest req, Model model){
        String username=req.getRemoteUser();
        User user = (User) this.userService.loadUserByUsername(username);
//        Order order =  this.orderService.getActiveOrder(user.getUsername());
        if(this.pizzaService.findById(id).isPresent()){
            Pizza pizza = this.pizzaService.findById(id).orElseThrow(PizzaNotFoundException::new);
            this.orderService.getActiveOrder(user.getUsername()).getPizza().remove(pizza);
            this.orderService.save(this.orderService.getActiveOrder(user.getUsername()));
        }

        model.addAttribute("pizzas", this.orderService.getActiveOrder(req.getRemoteUser()).getPizza());
        return "redirect:/remove";
    }
}

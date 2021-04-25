package webprogramming.project.service.impl;

import org.springframework.stereotype.Service;
import webprogramming.project.bootstrap.DataHolder;
import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.User;
import webprogramming.project.model.exceptions.PizzaAlreadyInOrderException;
import webprogramming.project.model.exceptions.PizzaNotFoundException;
import webprogramming.project.model.exceptions.UserNotFoundException;
import webprogramming.project.repository.OrderRepository;
import webprogramming.project.repository.PizzaRepository;
import webprogramming.project.repository.UserRepository;
import webprogramming.project.service.OrderService;
import webprogramming.project.service.PizzaService;
import webprogramming.project.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final PizzaService pizzaService;
    private final UserService userService;

    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository, PizzaRepository pizzaRepository, PizzaService pizzaService, UserService userService) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
        this.pizzaService = pizzaService;
        this.userService = userService;
    }

    @Override
    public Order addPizza(String username, Long id) {
        Order order = this.getActiveOrder(username);
        Pizza pizza = this.pizzaService.findById(id)
                .orElseThrow(PizzaNotFoundException::new);
        if(order.getPizza()
                .stream().filter(i -> i.getId().equals(id))
                .collect(Collectors.toList()).size() > 0)
            throw new PizzaAlreadyInOrderException(id, username);
        order.getPizza().add(pizza);
        return this.orderRepository.save(order);
    }

    @Override
    public Order getActiveOrder(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.orderRepository
                .findByUser(user)
                .orElseGet(() -> {
                    Order order = new Order(user);
                    return this.orderRepository.save(order);
                });
    }

    @Override
    public Optional<Order> save(Order order) {
        this.orderRepository.deleteById(order.getId());
        return Optional.of(this.orderRepository.save(order));
    }

    @Override
    public double calculateTotalCost(List<Long> pizzaIds, String delivery, Order order) {
        List<Pizza> pizzaList = new ArrayList<>();
        double totalCost = 0.0;
        int discount = 0;
        for (Long pizzaId : pizzaIds) {
            pizzaList.add(this.pizzaService.findById(pizzaId).orElseThrow(PizzaNotFoundException::new));
        }
        for (Pizza pizza : pizzaList) {
            discount++;
            if(discount % 3 == 0) {
                totalCost += pizza.getCost() * 0.5; //50% discount for every third pizza ordered
            }else{
                totalCost += pizza.getCost();
            }
        }
        if(totalCost >= 2000){
            totalCost *= 0.70; //30% discount before delivery cost if totalCost >= 2000
        }else if(totalCost >= 1500){
            totalCost *= 0.80; //20% discount before delivery cost if totalCost >= 1500
        }

        if(delivery.equals("Express Delivery")){
            totalCost+=200;
            order.setTimeUntilPizzaArrives("10 minutes");
        }else{
            totalCost+=100;
            order.setTimeUntilPizzaArrives("20 minutes");
        }
        return totalCost;
    }
}

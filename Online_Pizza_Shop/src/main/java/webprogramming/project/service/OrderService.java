package webprogramming.project.service;

import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order addPizza(String username, Long id);

    Order getActiveOrder(String username);

    Optional<Order> save(Order order);

    double calculateTotalCost(List<Long> pizzaIds, String delivery, Order order);
}

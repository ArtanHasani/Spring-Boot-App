package webprogramming.project.service;

import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface PizzaService {
    List<Pizza> findAll();

    Optional<Pizza> findById(Long id);

    Optional<Pizza> findByName(String name);

    Optional<Pizza> save(String name, String size, Double cost, String url, List<Long> ingredientsIds);

    Optional<Pizza> getPizzaFromName(String pizzaName);

    double calculateCost(List<Long> ingredients, String pizzaSize);
}

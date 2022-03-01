package serviceTests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import webprogramming.project.model.*;
import webprogramming.project.repository.IngredientsRepository;
import webprogramming.project.repository.PizzaRepository;
import webprogramming.project.repository.UserRepository;
import webprogramming.project.service.impl.OrderServiceImpl;
import webprogramming.project.service.impl.PizzaServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @MockBean
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaServiceImpl pizzaService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testCalculateTotalCost(){
        String username = "Veton"+Math.random();
        User user = userRepository.save(new User(username,
                "Xhumkar",
                "ASD",
                "234567890",
                Role.ROLE_ADMIN));

        ingredientsRepository.save(new Ingredients("ing1", 10.0, new Manufacturer()));
        ingredientsRepository.save(new Ingredients("ing2", 10.0, new Manufacturer()));
        ingredientsRepository.save(new Ingredients("ing3", 10.0, new Manufacturer()));

//        Pizza pizza1 = pizzaRepository.save(new Pizza("PizzaName1", "PizzaSize", 100.0, "PizzaUrl",ingredientsRepository.findAll()));
        Pizza pizza1 = pizzaRepository.findById((long)2).get();
        Order order = new Order(400.00, "20 minutes", user);

        order.getPizza().add(pizza1);
        List<Long> pizzaIds = new ArrayList<>();
        pizzaIds.add(pizza1.getId());
        assertEquals((orderService.calculateTotalCost(pizzaIds, "Regular Delivery", order)), 100);

    }
}

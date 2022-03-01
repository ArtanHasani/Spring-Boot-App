package serviceTests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import webprogramming.project.model.*;
import webprogramming.project.model.exceptions.IngredientIDInvalid;
import webprogramming.project.repository.IngredientsRepository;
import webprogramming.project.repository.PizzaRepository;
import webprogramming.project.repository.UserRepository;
import webprogramming.project.service.impl.PizzaServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PizzaServiceTest {

    @MockBean
    private PizzaRepository pizzaRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @InjectMocks
    private PizzaServiceImpl pizzaService;

    @Test
    public void testCalculateCost(){

        ingredientsRepository.save(new Ingredients("ing1", 10.0, new Manufacturer()));
        ingredientsRepository.save(new Ingredients("ing2", 10.0, new Manufacturer()));
        ingredientsRepository.save(new Ingredients("ing3", 10.0, new Manufacturer()));
        Pizza pizza = new Pizza("PizzaName", "PizzaSize", 0.0, "PizzaUrl",ingredientsRepository.findAll());
        List<Long> ingId = new ArrayList<>();
        for(Ingredients i : pizza.getIngredients()) {
                ingId.add(i.getId());
        }
        double cost = pizzaService.calculateCost(ingId, pizza.getSize());

        assertEquals(cost, 100);
    }
}

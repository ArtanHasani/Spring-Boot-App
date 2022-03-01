package repositoryTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import webprogramming.project.ProjectApplication;
import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Manufacturer;
import webprogramming.project.model.Pizza;
import webprogramming.project.repository.IngredientsRepository;
import webprogramming.project.repository.ManufacturerRepository;
import webprogramming.project.repository.PizzaRepository;;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Test
    public void testFindByName()
    {
        Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer("Vitaminka","Gostivar"));
        Ingredients ingredients = ingredientsRepository.save(
                new Ingredients(
                        "ketchup1",
                        13.00,
                        manufacturer));
        List<Ingredients> ingredientsList= new ArrayList<>();

        ingredientsList.add(ingredients);
        ingredientsList.add(ingredients);
        ingredientsList.add(ingredients);
        ingredientsList.add(ingredients);

        String name = "Margarita"+Math.random();
        Pizza pizza = pizzaRepository.save(new Pizza(name,"Medium",123.00,"",ingredientsList));

        assertEquals(pizzaRepository.findByName(pizza.getName()).get().getName(),pizza.getName());
    }

    @Test
    @Transactional
    public void testDeleteByName()
    {
        Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer("Vitaminka","Gostivar"));
        Ingredients ingredients = ingredientsRepository.save(
                new Ingredients(
                        "ketchup",
                        13.00,
                        manufacturer));
        List<Ingredients> ingredientsList= new ArrayList<>();

        ingredientsList.add(ingredients);
        ingredientsList.add(ingredients);
        ingredientsList.add(ingredients);
        ingredientsList.add(ingredients);
        Pizza pizza = pizzaRepository.save(new Pizza("Margarita","Medium",123.00,"",ingredientsList));

        pizzaRepository.deleteByName("Margarita");


        assertNotNull(pizzaRepository.findById(pizza.getId()));

    }

}
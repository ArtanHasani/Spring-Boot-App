package repositoryTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import webprogramming.project.ProjectApplication;
import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Manufacturer;
import webprogramming.project.repository.IngredientsRepository;
import webprogramming.project.repository.ManufacturerRepository;;import javax.transaction.Transactional;


@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IngredientsRepositoryTest {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Test
    @Transactional
    public void testDeleteByName()
    {
        Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer("Vitaminka","Gostivar"));
        Ingredients ingredients = ingredientsRepository.save(
                new Ingredients(
                        "asd",
                        13.00,
                        manufacturer));


       ingredientsRepository.deleteByName("asd");


        assertNotNull(ingredientsRepository.findById(ingredients.getId()));

    }
}
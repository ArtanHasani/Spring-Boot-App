package repositoryTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import webprogramming.project.ProjectApplication;
import webprogramming.project.model.Manufacturer;
import webprogramming.project.repository.ManufacturerRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ManufacturerRepositoryTest {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Test
    @Transactional
    public void testDeleteByName()
    {
        Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer("Vitaminka","Gostivar"));

        manufacturerRepository.deleteByName("Vitaminka");


        assertNotNull(manufacturerRepository.findById(manufacturer.getId()));

    }
}
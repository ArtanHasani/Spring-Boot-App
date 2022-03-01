package repositoryTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import webprogramming.project.ProjectApplication;

import webprogramming.project.model.Role;
import webprogramming.project.model.User;
import webprogramming.project.repository.IngredientsRepository;
import webprogramming.project.repository.ManufacturerRepository;
import webprogramming.project.repository.PizzaRepository;
import webprogramming.project.repository.UserRepository;;import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;



    @Test
    public void testFindByUsernameAndPassword(){
        String username = "Veton"+Math.random();
        User user = userRepository.save(new User(username,
                "Xhumkar",
                "ASD",
                "234567890",
                Role.ROLE_ADMIN));

        assertEquals(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).get(), user);
    }

    @Test
    public void testFindByUsername(){
        String username = "Veton"+Math.random();
        User user = userRepository.save(new User(username,
                "Xhumkar",
                "ASD",
                "234567890",
                Role.ROLE_ADMIN));

        assertEquals(userRepository.findByUsername(user.getUsername()).get(), user);
    }


}
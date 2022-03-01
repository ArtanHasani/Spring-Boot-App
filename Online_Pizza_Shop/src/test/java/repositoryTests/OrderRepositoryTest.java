package repositoryTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import webprogramming.project.ProjectApplication;
import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.Role;
import webprogramming.project.model.User;
import webprogramming.project.repository.OrderRepository;
import webprogramming.project.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    void testFindByUser(){

        String username = "Veton"+Math.random();
        User user = userRepository.save(new User(username,
                "Xhumkar",
                "ASD",
                "234567890",
                Role.ROLE_ADMIN));

        Order order = orderRepository.save(new Order(user));
        assertThat(orderRepository.findByUser(user).get().getId()).isGreaterThan(0);

    }
}
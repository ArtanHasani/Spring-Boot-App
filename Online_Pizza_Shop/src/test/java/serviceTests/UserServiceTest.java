package serviceTests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import webprogramming.project.model.Role;
import webprogramming.project.repository.UserRepository;
import webprogramming.project.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static webprogramming.project.model.Role.ROLE_ADMIN;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testRegister(){
        String username = "Veton";

        String address = "ASD";

        String credit_card_number = "234567890";

        String password = "Xhumkar";

        assertNotNull(userService.register(username, password, password, address, credit_card_number, ROLE_ADMIN));
    }
}

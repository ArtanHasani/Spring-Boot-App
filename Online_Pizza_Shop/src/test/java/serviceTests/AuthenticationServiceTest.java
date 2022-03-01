package serviceTests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import webprogramming.project.model.Role;
import webprogramming.project.model.User;
import webprogramming.project.repository.UserRepository;
import webprogramming.project.service.AuthenticationService;
import webprogramming.project.service.impl.AuthenticationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AuthenticationServiceTest {


    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void testLogin(){
        User user = new User("veton",
                "veton",
                "asd",
                "1234123412341234",
                Role.ROLE_ADMIN);

        Mockito.when(authenticationService.login(user.getUsername(), user.getPassword())).thenReturn(user) ;
        assertNotNull(user);
    }


}

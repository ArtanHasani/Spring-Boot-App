package controllerTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import webprogramming.project.ProjectApplication;
import webprogramming.project.config.CustomUsernamePasswordAuthenticationProvider;
import webprogramming.project.model.Order;
import webprogramming.project.model.Pizza;
import webprogramming.project.model.Role;
import webprogramming.project.model.User;
import webprogramming.project.repository.UserRepository;
import webprogramming.project.service.OrderService;
import webprogramming.project.service.impl.OrderServiceImpl;
import webprogramming.project.service.impl.PizzaServiceImpl;
import webprogramming.project.service.impl.UserServiceImpl;
import webprogramming.project.web.controller.OrderController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = ProjectApplication.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl orderService;

    @MockBean
    private PizzaServiceImpl pizzaService;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private CustomUsernamePasswordAuthenticationProvider provider;

    @MockBean
    private UserRepository userRepository;


    //Post method Test
    @Test
    public void testMakeOrder() throws Exception {


        User user = new User("asd",
                "asd",
                "ASD",
                "234567890",
                Role.ROLE_ADMIN);

        Mockito.when(userService.loadUserByUsername(user.getUsername())).thenReturn(user);

        Order order = new Order(user);
        Mockito.when(orderService.getActiveOrder(user.getUsername())).thenReturn(order);

        List<Pizza> pizzas = pizzaService.findAll();
        List<Long> pizzaIds = new ArrayList<>();
        for(Pizza p : pizzas){
            pizzaIds.add(p.getId());
        }

        double totalCost = 0.0;
        Mockito.when(orderService.calculateTotalCost(pizzaIds, "Regular Delivery", order)).thenReturn(totalCost);

        String url = "/order/make-order";

        MvcResult mvcResult = mockMvc.perform(post(url)).andExpect(status().is3xxRedirection()).andReturn();

    }

    //Get method Test
    @Test
    public void testConfirmOrder() throws Exception {


        User user = new User("asd",
                "asd",
                "ASD",
                "234567890",
                Role.ROLE_ADMIN);

        Mockito.when(userService.loadUserByUsername(user.getUsername())).thenReturn(user);

        Order order = new Order(user);
        Mockito.when(orderService.getActiveOrder(user.getUsername())).thenReturn(order);

        order.setCost(10.0);
        order.setTimeUntilPizzaArrives("10");
        String url = "/order/confirm";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk()).andReturn();
    }
}

package webprogramming.project.bootstrap;

import lombok.Getter;
import org.springframework.stereotype.Component;
import webprogramming.project.model.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Pizza> pizzas = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Ingredients> ingredients = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

}

package webprogramming.project.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cost;

    private String timeUntilPizzaArrives;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Pizza> pizza;

    public Order() {
    }

    public Order(Double cost, String timeUntilPizzaArrives, User user) {
        this.cost = cost;
        this.timeUntilPizzaArrives = timeUntilPizzaArrives;
        this.pizza = new ArrayList<>();
        this.user = user;
    }
    public Order(User user) {
        this.cost = 0.0;
        this.timeUntilPizzaArrives = "";
        this.pizza = new ArrayList<>();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Double getCost() {
        return cost;
    }

    public String getTimeUntilPizzaArrives() {
        return timeUntilPizzaArrives;
    }

    public User getUser() {
        return user;
    }

    public List<Pizza> getPizza() {
        return pizza;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setTimeUntilPizzaArrives(String timeUntilPizzaArrives) {
        this.timeUntilPizzaArrives = timeUntilPizzaArrives;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPizza(List<Pizza> pizza) {
        this.pizza = pizza;
    }
}

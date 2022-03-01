package webprogramming.project.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String size;

    private Double cost;

    private String url;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ingredients> ingredients;

    public Pizza() {
    }

    public Pizza(String name, String size, Double cost, String url, List<Ingredients> ingredients) {
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.url = url;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public Double getCost() {
        return cost;
    }

    public String getUrl() {
        return url;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }
}

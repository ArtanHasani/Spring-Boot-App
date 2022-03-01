package webprogramming.project.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double cost;

    @ManyToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;

    public Ingredients() {
    }

    public Ingredients(String name, Double cost, Manufacturer manufacturer) {
        this.name = name;
        this.cost = cost;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return cost;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}

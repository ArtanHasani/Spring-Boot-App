package webprogramming.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webprogramming.project.model.Pizza;

import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findByName(String name);
    void deleteByName(String name);
}

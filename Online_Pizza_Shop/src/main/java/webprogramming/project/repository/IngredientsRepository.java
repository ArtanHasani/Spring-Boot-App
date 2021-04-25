package webprogramming.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webprogramming.project.model.Ingredients;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    void deleteByName(String name);
}

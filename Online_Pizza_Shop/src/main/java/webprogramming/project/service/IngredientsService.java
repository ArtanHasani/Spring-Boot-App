package webprogramming.project.service;

import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface IngredientsService {

    Optional<Ingredients> findById(Long id);
    List<Ingredients> findAll();
    Optional<Ingredients> save(String name, Double cost, Long manId);
}

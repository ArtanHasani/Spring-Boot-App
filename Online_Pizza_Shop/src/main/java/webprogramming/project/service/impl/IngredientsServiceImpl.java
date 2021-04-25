package webprogramming.project.service.impl;

import org.springframework.stereotype.Service;
import webprogramming.project.bootstrap.DataHolder;
import webprogramming.project.model.Ingredients;
import webprogramming.project.model.Manufacturer;
import webprogramming.project.model.exceptions.IngredientIDInvalid;
import webprogramming.project.model.exceptions.ManufacturerNotFoundException;
import webprogramming.project.model.exceptions.PizzaNotFoundException;
import webprogramming.project.repository.IngredientsRepository;
import webprogramming.project.repository.ManufacturerRepository;
import webprogramming.project.service.IngredientsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private final ManufacturerRepository manufacturerRepository;
    private final IngredientsRepository ingredientsRepository;

    public IngredientsServiceImpl(ManufacturerRepository manufacturerRepository, IngredientsRepository ingredientsRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Optional<Ingredients> findById(Long id) {
        return this.ingredientsRepository.findById(id);
    }

    @Override
    public List<Ingredients> findAll() {
        return this.ingredientsRepository.findAll();
    }

    @Override
    public Optional<Ingredients> save(String name, Double cost, Long manId) {
        if(name.equals("")){
            throw new IngredientIDInvalid();
        }
        Manufacturer manufacturer = this.manufacturerRepository.findById(manId).orElseThrow(() -> new ManufacturerNotFoundException(manId));
        this.ingredientsRepository.deleteByName(name);
        return Optional.of(this.ingredientsRepository.save(new Ingredients(name, cost, manufacturer)));
    }
}

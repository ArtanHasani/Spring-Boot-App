package webprogramming.project.service;

import webprogramming.project.model.Manufacturer;
import webprogramming.project.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    Optional<Manufacturer> findById(Long id);
    List<Manufacturer> findAll();
    Optional<Manufacturer> save(String name, String CoO);
}

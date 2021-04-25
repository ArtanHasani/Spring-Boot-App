package webprogramming.project.service.impl;

import org.springframework.stereotype.Service;
import webprogramming.project.model.Manufacturer;
import webprogramming.project.repository.ManufacturerRepository;
import webprogramming.project.service.ManufacturerService;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name, String CoO) {
        this.manufacturerRepository.deleteByName(name);
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, CoO)));
    }
}

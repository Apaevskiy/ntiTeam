package ru.paevskiy.ntiTeam.service;


import org.springframework.stereotype.Service;
import ru.paevskiy.ntiTeam.entity.Planet;
import ru.paevskiy.ntiTeam.repository.PlanetRepository;

import java.util.List;

@Service
public class PlanetService {
    private final PlanetRepository repository;

    public PlanetService(PlanetRepository repository) {
        this.repository = repository;
    }

    public List<Planet> getAllPlanet() {
        return repository.findAll();

    }

    public Planet getPlanet(long id) {
        return repository.getOne(id);
    }

    public void createPlanet(Planet planet) {
        System.out.println(planet);
        if(planet.getLord().getId()==0)
            planet.setLord(null);
        repository.save(planet);
    }

    public void destroyPlanet(long id) { // Уничтожить Планету
        repository.deleteById(id);
    }

    public void appointALord(Planet planet) {
        repository.saveAndFlush(planet);
    }
}

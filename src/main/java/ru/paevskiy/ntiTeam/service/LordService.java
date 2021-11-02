package ru.paevskiy.ntiTeam.service;

import org.springframework.stereotype.Service;
import ru.paevskiy.ntiTeam.entity.Lord;
import ru.paevskiy.ntiTeam.repository.LordRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LordService {
    private final LordRepository repository;

    public LordService(LordRepository repository) {
        this.repository = repository;
    }

    public List<Lord> getAllLords() {
        return repository.findAll();
    }

    public Lord getLord(long id) {
        return repository.getOne(id);
    }

    public void createLord(Lord lord) {
        repository.save(lord);
    }

    public List<Lord> getTop() {
        return repository.findTop10ByOrderByAgeAsc();
    }

    public List<Lord> getParasites() {
        return repository.findAllByPlanetsIsNull();
    }

    public void updateLord(Lord lord) {
        repository.saveAndFlush(lord);
    }
}

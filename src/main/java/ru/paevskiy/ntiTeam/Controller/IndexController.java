package ru.paevskiy.ntiTeam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.paevskiy.ntiTeam.DAO.ChaosLordService;
import ru.paevskiy.ntiTeam.Models.ChaosLord;
import ru.paevskiy.ntiTeam.Models.Planet;

@RestController
public class IndexController {
    private final ChaosLordService service;

    @Autowired
    public IndexController(ChaosLordService service) {
        this.service = service;
    }
    // Создание нового лорда
    @PostMapping(value = "/lord")
    public ResponseEntity<?> createLord(@RequestBody ChaosLord lord) {
        System.out.println("Create: " + lord);
        service.createLord(lord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // Создание новой планеты
    @PostMapping(value = "/planet")
    public ResponseEntity<?> createPlanet(@RequestBody Planet planet) {
        System.out.println("Create: " + planet);
        service.createPlanet(planet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // Назначение нового правителя
    @PutMapping(value = "/planet/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody ChaosLord lord) {
        System.out.println("Update: "+lord);
        final boolean updated = service.appointALord(lord, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    // Удаление пользователя по id
    @DeleteMapping(value = "/mobileCatalog/{id}")
    public ResponseEntity<?> destroyPlanet(@PathVariable(name = "id") int id) {
        System.out.println("destroy: " + id);
        final boolean deleted = service.destroyPlanet(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

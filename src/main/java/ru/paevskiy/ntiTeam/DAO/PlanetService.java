package ru.paevskiy.ntiTeam.DAO;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.paevskiy.ntiTeam.Models.Planet;

import java.util.List;

@Service
public class PlanetService {
    private final JdbcTemplate jdbcTemplate;

    public PlanetService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Planet> getAllPlanet() {
        return jdbcTemplate.query("select pl.planetid,pl.nameofplanet, l.lordid, l.fullname, l.age from planets pl " +
                        "join lords l on l.lordid = pl.lordid order by pl.planetid",
                new PlanetMapper());

    }

    public Planet getPlanet(int id) {
        return jdbcTemplate.queryForObject("select pl.planetid,pl.nameofplanet, l.lordid, l.fullname, l.age from planets pl " +
                        "join lords l on l.lordid = pl.lordid where pl.planetid=?",
                new PlanetMapper(), id);
    }

    public void createPlanet(Planet planet) {
        if (existsById(planet.getChaosLord().getId())) {
            jdbcTemplate.update("insert into planets (nameofplanet, lordid) values (?,?)",
                    planet.getNameOfPlanet(), planet.getChaosLord().getId());
        }

    }

    public void destroyPlanet(int id) { // Уничтожить Планету
        if (existsById(id)) {
            jdbcTemplate.update("delete from planets where planetid = ?", id);
        }
    }

    public void appointALord(Planet planet) {
        if (existsById(planet.getChaosLord().getId())) {
            jdbcTemplate.update("update public.planets set lordid=? where planetid=?",
                    planet.getChaosLord().getId(), planet.getId());
        }
    }

    public boolean existsById(int id) {
        Integer count = jdbcTemplate.queryForObject(
                "select COUNT(*) from planets where planetid = ? limit 1",
                Integer.class, id);
        return count != null && count != 0;
    }
}

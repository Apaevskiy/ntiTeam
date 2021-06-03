package ru.paevskiy.ntiTeam.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.paevskiy.ntiTeam.Models.Planet;

public class ChaosLordService {
    private final JdbcTemplate jdbcTemplate;

    public ChaosLordService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void createPlanet(Planet planet) {
        String save = "";
        jdbcTemplate.update(save);
    }
}

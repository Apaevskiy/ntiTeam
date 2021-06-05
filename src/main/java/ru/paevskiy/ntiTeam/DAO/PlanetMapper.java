package ru.paevskiy.ntiTeam.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.paevskiy.ntiTeam.Models.ChaosLord;
import ru.paevskiy.ntiTeam.Models.Planet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanetMapper implements RowMapper<Planet> {
    @Override
    public Planet mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Planet(
                resultSet.getInt("planetId"),
                resultSet.getString("nameOfPlanet"),
                new ChaosLord(
                        resultSet.getInt("lordId"),
                        resultSet.getString("fullName"),
                        resultSet.getInt("age"))
        );
    }
}

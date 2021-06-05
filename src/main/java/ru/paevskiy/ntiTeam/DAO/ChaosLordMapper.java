package ru.paevskiy.ntiTeam.DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.paevskiy.ntiTeam.Models.ChaosLord;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChaosLordMapper implements RowMapper<ChaosLord> {
    @Override
    public ChaosLord mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ChaosLord(
                resultSet.getInt("lordId"),
                resultSet.getString("fullName"),
                resultSet.getInt("age")
        );
    }
}

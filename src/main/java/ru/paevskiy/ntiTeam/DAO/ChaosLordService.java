package ru.paevskiy.ntiTeam.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.paevskiy.ntiTeam.Models.ChaosLord;
import ru.paevskiy.ntiTeam.Models.Planet;

@Service
public class ChaosLordService {
    private final JdbcTemplate jdbcTemplate;

    public ChaosLordService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createPlanet(Planet planet) {
        String save = "";
        jdbcTemplate.update(save);
    }

    public void createLord(ChaosLord chaosLord) {
        String save = "";
        jdbcTemplate.update(save);
    }

    public boolean appointALord(ChaosLord lord, int id) {
        if (existsById(id)) {
            String strUpdate = "update public.planets set " +
                    "lordid='" + lord.getName() +
                    "' where planetid=" + id;
            jdbcTemplate.update(strUpdate);
            return true;
        }
        return false;
    }

    // Проверка, есть ли пользователь с таким id
    public boolean existsById(int id) {
        String sql = "select COUNT(*) from lords where lordid = '" + id + "' limit 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count != 0;
    }

    public boolean destroyPlanet(int id) { // Уничтожить Планету
        if (existsById(id)) {
            String strDelete = "delete from planets where planetid = " + id;
            jdbcTemplate.update(strDelete);
            return true;
        }
        return false;
    }
}

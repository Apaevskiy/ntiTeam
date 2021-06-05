package ru.paevskiy.ntiTeam.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.paevskiy.ntiTeam.Models.ChaosLord;

import java.util.List;

@Service
public class ChaosLordService {
    private final JdbcTemplate jdbcTemplate;

    public ChaosLordService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ChaosLord> getAllLords() {
        return jdbcTemplate.query("select * from lords order by lordid", new ChaosLordMapper());
    }

    public ChaosLord getLord(int id) {
        return jdbcTemplate.queryForObject("select * from lords where lordid=?", new ChaosLordMapper(), id);
    }

    public void createLord(ChaosLord chaosLord) {
        jdbcTemplate.update("insert into lords (fullname, age) values (?,?)",
                chaosLord.getName(), chaosLord.getAge());
    }

    public List<ChaosLord> getTop() {
        return jdbcTemplate.query("select * from lords l order by l.age limit 10",
                new ChaosLordMapper());
    }

    public List<ChaosLord> getParasites() {
        return jdbcTemplate.query("select * from lords l where lordid not in (select distinct pl.lordid from planets pl)",
                new ChaosLordMapper());
    }

    public void updateLord(ChaosLord lord) {
        if (existsById(lord.getId())) {
            jdbcTemplate.update("update public.lords set fullname=?, age=? where lordid=?",
                    lord.getName(), lord.getAge(), lord.getId());
        }
    }

    public boolean existsById(int id) {
        Integer count = jdbcTemplate.queryForObject(
                "select COUNT(*) from lords where lordid = ? limit 1",
                Integer.class, id);
        return count != null && count != 0;
    }
}

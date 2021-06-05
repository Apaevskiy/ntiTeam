package ru.paevskiy.ntiTeam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.paevskiy.ntiTeam.DAO.ChaosLordMapper;
import ru.paevskiy.ntiTeam.DAO.ChaosLordService;
import ru.paevskiy.ntiTeam.DAO.PlanetService;
import ru.paevskiy.ntiTeam.Models.ChaosLord;
import ru.paevskiy.ntiTeam.Models.Planet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NtiTeamApplication.class)
public class tests {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Before
    public void before() {
        jdbcTemplate.update(readFileSql("src/test/java/ru/paevskiy/ntiTeam/initDB.sql"));
        jdbcTemplate.update(readFileSql("src/test/java/ru/paevskiy/ntiTeam/populateDB.sql"));
        System.out.println(jdbcTemplate.query("select * from lords", new ChaosLordMapper()));
    }

    @Test
    public void testCreateLord() {
        ChaosLordService service = new ChaosLordService(jdbcTemplate);

        int id = 12;
        ChaosLord expectedLord = new ChaosLord(id, "testLord", 213);
        service.createLord(expectedLord);

        ChaosLord actualLord = service.getLord(id);

        Assert.assertEquals(actualLord, expectedLord);
    }

    @Test
    public void testCreatePlanet() {
        PlanetService service = new PlanetService(jdbcTemplate);

        int id = 16;
        Planet expectedPlanet = new Planet(16, "testPlanet", null);
        expectedPlanet.setChaosLord(new ChaosLord(3, "Верделет", 245));

        service.createPlanet(expectedPlanet);

        Planet actualPlanet = service.getPlanet(id);

        Assert.assertEquals(actualPlanet, expectedPlanet);
    }

    @Test
    public void testSetLord() {
        PlanetService service = new PlanetService(jdbcTemplate);
        Planet expectedPlanet = service.getPlanet(3);
        expectedPlanet.setChaosLord(new ChaosLord(3, "Верделет", 245));

        service.appointALord(expectedPlanet);

        Planet actualPlanet = service.getPlanet(3);

        Assert.assertEquals(actualPlanet.getChaosLord(), expectedPlanet.getChaosLord());
    }

    @Test
    public void testDestroyPlanet() {
        PlanetService service = new PlanetService(jdbcTemplate);

        Planet expectedPlanet = service.getPlanet(3);
        expectedPlanet.setChaosLord(new ChaosLord(3, "Верделет", 245));

        service.destroyPlanet(3);

        Assert.assertFalse(service.existsById(3));
    }

    @Test
    public void testTop() {
        ChaosLordService service = new ChaosLordService(jdbcTemplate);

        List<ChaosLord> expectedList = new ArrayList<>();
        expectedList.add(new ChaosLord(5, "lord 1", 20));
        expectedList.add(new ChaosLord(8, "lord 4", 124));
        expectedList.add(new ChaosLord(10, "lord 6", 158));
        expectedList.add(new ChaosLord(7, "lord 3", 164));
        expectedList.add(new ChaosLord(9, "lord 5", 168));
        expectedList.add(new ChaosLord(2, "Асмодей", 189));
        expectedList.add(new ChaosLord(3, "Верделет", 245));
        expectedList.add(new ChaosLord(1, "Бельфегор", 385));
        expectedList.add(new ChaosLord(4, "Инкубус", 478));
        expectedList.add(new ChaosLord(11, "lord 7", 741));

        Assert.assertEquals(expectedList, service.getTop());
    }

    @Test
    public void testParasites() {
        ChaosLordService service = new ChaosLordService(jdbcTemplate);

        List<ChaosLord> expectedList = new ArrayList<>();
        expectedList.add(new ChaosLord(6, "lord 2", 844));
        expectedList.add(new ChaosLord(8, "lord 4", 124));
        expectedList.add(new ChaosLord(10, "lord 6", 158));

        Assert.assertEquals(expectedList, service.getParasites());
    }

    private String readFileSql(String pathToFile) {
        StringBuilder sb = new StringBuilder();
        File file = new File(pathToFile);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

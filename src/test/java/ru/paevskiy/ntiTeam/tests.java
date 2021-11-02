package ru.paevskiy.ntiTeam;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.paevskiy.ntiTeam.entity.Lord;
import ru.paevskiy.ntiTeam.entity.Planet;
import ru.paevskiy.ntiTeam.repository.LordRepository;
import ru.paevskiy.ntiTeam.repository.PlanetRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = NtiTeamApplication.class)
public class tests {

    @Autowired
    private LordRepository lordRepository;
    @Autowired
    private PlanetRepository planetRepository;



    /*@Before
    public void before() {
        jdbcTemplate.update(readFileSql("src/test/java/ru/paevskiy/ntiTeam/initDB.sql"));
        jdbcTemplate.update(readFileSql("src/test/java/ru/paevskiy/ntiTeam/populateDB.sql"));
        System.out.println(jdbcTemplate.query("select * from lords", new LordMapper()));
    }*/

    @Test
    public void testCreateLord() {
        Lord lord = new Lord();
        lord.setName("testLord");
        lord.setAge(123);
        lordRepository.save(lord);
        Assertions.assertThat(lord.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreatePlanet() {
        Planet expectedPlanet = new Planet();
        expectedPlanet.setName("testPlanet");
        planetRepository.save(expectedPlanet);
        Assertions.assertThat(expectedPlanet.getId()).isGreaterThan(0);
    }

    @Test
    public void testSetLord() {
        Planet expectedPlanet = new Planet();
        expectedPlanet.setName("testPlanet1");
        planetRepository.save(expectedPlanet);
        Lord lord = new Lord();
        lord.setName("testLord1");
        lord.setAge(1234);
        lordRepository.save(lord);
        expectedPlanet.setLord(lord);
        planetRepository.saveAndFlush(expectedPlanet);
        Assertions.assertThat(expectedPlanet.getLord()).isNotNull();
    }

    @Test
    public void testDestroyPlanet() {
        Planet expectedPlanet = new Planet();
        expectedPlanet.setName("testPlanet");
        planetRepository.save(expectedPlanet);
        System.out.println(expectedPlanet.getId());
        planetRepository.delete(expectedPlanet);
        Planet planet = planetRepository.getOne(expectedPlanet.getId());
        Assert.assertNull(planet.getName());
    }

    @Test
    public void testTop() {
        /*List<Lord> expectedList = new ArrayList<>();
        expectedList.add(new Lord(5, "lord 1", 20));
        expectedList.add(new Lord(8, "lord 4", 124));
        expectedList.add(new Lord(10, "lord 6", 158));
        expectedList.add(new Lord(7, "lord 3", 164));
        expectedList.add(new Lord(9, "lord 5", 168));
        expectedList.add(new Lord(2, "Асмодей", 189));
        expectedList.add(new Lord(3, "Верделет", 245));
        expectedList.add(new Lord(1, "Бельфегор", 385));
        expectedList.add(new Lord(4, "Инкубус", 478));
        expectedList.add(new Lord(11, "lord 7", 741));

        Assert.assertEquals(expectedList, lordRepository.getTop());*/
    }

    @Test
    public void testParasites() {
        Lord lord = new Lord();
        lord.setName("testLord");
        lordRepository.save(lord);
        Assert.assertTrue(lordRepository.findAllByPlanetsIsNull().contains(lord));
    }
}
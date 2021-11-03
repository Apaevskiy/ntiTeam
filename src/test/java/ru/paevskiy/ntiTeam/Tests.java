package ru.paevskiy.ntiTeam;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.paevskiy.ntiTeam.entity.Lord;
import ru.paevskiy.ntiTeam.entity.Planet;
import ru.paevskiy.ntiTeam.repository.LordRepository;
import ru.paevskiy.ntiTeam.repository.PlanetRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NtiTeamApplication.class)
public class Tests {

    @Autowired
    private LordRepository lordRepository;
    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void testCreateLord() {
        Lord lord = new Lord();
        lord.setName("testLord");
        lord.setAge(123);
        lordRepository.save(lord);
        Assert.assertTrue(lordRepository.findById(lord.getId()).isPresent());
    }

    @Test
    public void testCreatePlanet() {
        Planet expectedPlanet = new Planet();
        expectedPlanet.setName("testPlanet");
        planetRepository.save(expectedPlanet);
        Assert.assertTrue(planetRepository.findById(expectedPlanet.getId()).isPresent());
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
        Optional<Planet> actualOptionalPlanet = planetRepository.findById(expectedPlanet.getId());
        Assert.assertTrue(actualOptionalPlanet.isPresent() && actualOptionalPlanet.get().getLord() != null);
    }

    @Test
    public void testDestroyPlanet() {
        Planet expectedPlanet = new Planet();
        expectedPlanet.setName("testPlanet");
        planetRepository.save(expectedPlanet);
        System.out.println(expectedPlanet.getId());
        planetRepository.delete(expectedPlanet);
        Optional<Planet> planet = planetRepository.findById(expectedPlanet.getId());
        Assert.assertFalse(planet.isPresent());
    }

    @Test
    public void testTop() {
        List<Lord> actualList = lordRepository.findTop10ByOrderByAgeAsc();
        List<Integer> ages = actualList.stream().map(Lord::getAge).collect(Collectors.toList());
        Assert.assertEquals(ages.stream().sorted().collect(Collectors.toList()), ages);
    }

    @Test
    public void testParasites() {
        Lord lord = new Lord();
        lord.setName("testLord");
        lord.setPlanets(new HashSet<>());
        lordRepository.save(lord);
        Assert.assertTrue(lordRepository.findAllByPlanetsIsNull().contains(lord));
    }
}
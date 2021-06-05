package ru.paevskiy.ntiTeam.Models;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Planet {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String nameOfPlanet;
    private ChaosLord chaosLord;

    public Planet() {
    }

    public Planet(int id, String nameOfPlanet, ChaosLord chaosLord) {
        this.id = id;
        this.nameOfPlanet = nameOfPlanet;
        this.chaosLord = chaosLord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfPlanet() {
        return nameOfPlanet;
    }

    public void setNameOfPlanet(String nameOfPlanet) {
        this.nameOfPlanet = nameOfPlanet;
    }

    public ChaosLord getChaosLord() {
        return chaosLord;
    }

    public void setChaosLord(ChaosLord chaosLord) {
        this.chaosLord = chaosLord;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", nameOfPlanet='" + nameOfPlanet + '\'' +
                ", chaosLord=" + chaosLord +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return id == planet.id && nameOfPlanet.equals(planet.nameOfPlanet) && chaosLord.equals(planet.chaosLord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfPlanet, chaosLord);
    }
}

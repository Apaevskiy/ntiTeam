package ru.paevskiy.ntiTeam.Models;

import org.springframework.stereotype.Component;

@Component
public class Planet {
    private int id;
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
}

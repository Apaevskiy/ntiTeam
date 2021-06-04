package ru.paevskiy.ntiTeam.Models;

public class Planet {
    private int id;
    private String nameOfPlanet;
    private int idOfLord;

    public Planet() {
    }

    public Planet(int id, String nameOfPlanet, int idOfLord) {
        this.id = id;
        this.nameOfPlanet = nameOfPlanet;
        this.idOfLord = idOfLord;
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

    public int getIdOfLord() {
        return idOfLord;
    }

    public void setIdOfLord(int idOfLord) {
        this.idOfLord = idOfLord;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", nameOfPlanet='" + nameOfPlanet + '\'' +
                ", idOfLord=" + idOfLord +
                '}';
    }
}

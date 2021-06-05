package ru.paevskiy.ntiTeam.Models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class ChaosLord {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    public ChaosLord() {
    }

    public ChaosLord(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ChaosLord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChaosLord lord = (ChaosLord) o;
        return id == lord.id && age == lord.age && name.equals(lord.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}

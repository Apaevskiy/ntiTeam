package ru.paevskiy.ntiTeam.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "lords")
public class Lord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lord")
    private Set<Planet> planets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lord lord = (Lord) o;
        return id == lord.id &&
                age == lord.age &&
                Objects.equals(name, lord.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    public Lord(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

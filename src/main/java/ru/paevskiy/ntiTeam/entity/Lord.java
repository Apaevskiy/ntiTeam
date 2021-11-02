package ru.paevskiy.ntiTeam.entity;

import lombok.*;

import javax.persistence.*;
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

    public Lord(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

package ru.paevskiy.ntiTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.paevskiy.ntiTeam.entity.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
}

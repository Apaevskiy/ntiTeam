package ru.paevskiy.ntiTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.paevskiy.ntiTeam.entity.Lord;
import ru.paevskiy.ntiTeam.entity.Planet;

import java.util.List;
import java.util.Optional;

public interface LordRepository extends JpaRepository<Lord, Long> {
    List<Lord> findAllByPlanetsIsNull();
    List<Lord> findTop10ByOrderByAgeAsc();
}

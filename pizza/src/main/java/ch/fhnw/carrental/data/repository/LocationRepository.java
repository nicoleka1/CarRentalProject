package ch.fhnw.carrental.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.carrental.data.domain.Location;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findLocationBylocationCity(String locationCity);
    List<Location> findAllBylocationCityContainsIgnoreCase(String locationCity);
}



package ch.fhnw.carrental.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.carrental.data.domain.Car;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarModel(String carBrand);
    List<Car> findAllByCarBrandContainsIgnoreCase(String carBrand);
}

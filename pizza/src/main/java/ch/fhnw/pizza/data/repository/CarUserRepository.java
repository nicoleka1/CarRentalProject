package ch.fhnw.pizza.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.CarUser;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface CarUserRepository extends JpaRepository<CarUser, Long> {
    CarUser findCarUserBycarUserEmail(String carUserEmail);
    List<CarUser> findAllBycarUserEmailContainsIgnoreCase(String carUserEmail);
}

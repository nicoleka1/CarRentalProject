package ch.fhnw.pizza.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Rental;

@Repository
//JpaRepository should be typed to the domain class and an ID type

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Rental findRentalByrentalAdminID(Long rentalAdminID);
    List<Rental> findRentalByrentalAdminID(Long rentalAdminID);
}

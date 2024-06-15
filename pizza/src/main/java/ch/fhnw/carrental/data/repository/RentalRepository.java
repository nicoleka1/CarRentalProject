package ch.fhnw.carrental.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.carrental.data.domain.Rental;

@Repository


public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllByRentalID(Long rentalID);
}

//JpaRepository should be typed to the domain class and an ID type

/*
public interface RentalRepository extends JpaRepository<Rental, Long> {
    Rental findRentalByrentalID(Long rentalID);
    List<Rental> findAllByrentalIDContainsIgnoreCase(Long rentalID);
}
*/
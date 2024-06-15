package ch.fhnw.carrental.business.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.carrental.data.domain.Rental;
import ch.fhnw.carrental.data.repository.RentalRepository;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    // Returns a rental by ID
    public Rental findRentalByrentalID(Long id) {
        try {
            Rental rental = rentalRepository.findById(id).get();
            return rental;
        } catch (Exception e) {
            throw new RuntimeException("Rental with id " + id + " not found");
        }
    }

    // Returns a list of all rentals
    public List<Rental> getAllRentals() {
        List<Rental> rentalList = rentalRepository.findAll();
        return rentalList;
    }

    // Adds a rental
    public Rental addRental(Rental rental) throws Exception {
        return rentalRepository.save(rental);
    }

    // Updates a rental by ID
    public Rental updateRental(Long id, Rental rental) throws Exception {
        Rental rentalToUpdate = rentalRepository.findById(id).get();
        if(rentalToUpdate != null) {
            if(rental.getRentalUserID() != null)
                rentalToUpdate.setRentalUserID(rental.getRentalUserID());
            if(rental.getRentalStartDate()!=null)
                rentalToUpdate.setRentalStartDate(rental.getRentalStartDate());
            if(rental.getRentalEndDate()!=null)
                rentalToUpdate.setRentalEndDate(rental.getRentalEndDate());
            if(rental.getRentalTotalCost()!=null)
                rentalToUpdate.setRentalTotalCost(rental.getRentalTotalCost());
            if(rental.getRentalStatus()!=null)
                rentalToUpdate.setRentalStatus(rental.getRentalStatus());
            return rentalRepository.save(rentalToUpdate);
            
        }            
        throw new Exception("Rental with id " + id + " does not exist");
    }

    // Deletes a rental by ID
    public void deleteRental(Long id) throws Exception {
        if(rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else
            throw new Exception("Rental with id " + id + " does not exist");
    }


    // Returns a list of rentals that are unavailable between the given dates
    public List<Rental> getUnavailableRentals(LocalDate startDate, LocalDate endDate) {
        List<Rental> rentalList = rentalRepository.findAll();
        List<Rental> unavailableRentals = new ArrayList<>();
    
        for (Rental rental : rentalList) {
            if ((startDate.isAfter(rental.getRentalStartDate()) || startDate.isEqual(rental.getRentalStartDate())) &&
                (startDate.isBefore(rental.getRentalEndDate()) || startDate.isEqual(rental.getRentalEndDate())) ||
                (endDate.isAfter(rental.getRentalStartDate()) || endDate.isEqual(rental.getRentalStartDate())) &&
                (endDate.isBefore(rental.getRentalEndDate()) || endDate.isEqual(rental.getRentalEndDate())) ||
                (startDate.isBefore(rental.getRentalStartDate()) && endDate.isAfter(rental.getRentalStartDate()))) {
                unavailableRentals.add(rental);
            }
        }
        return unavailableRentals;
    }

    // Returns a list of rental car IDs that are unavailable between the given dates
    public Long[] getUnavailableRentalCarIDs(LocalDate startDate, LocalDate endDate) {
        List<Rental> unavailableRentals = getUnavailableRentals(startDate, endDate);
        List<Long> rentalCarIDs = new ArrayList<>();
        
        for (Rental rental : unavailableRentals) {
            Long rentalCarID = rental.getRentalCarId();
            if (!rentalCarIDs.contains(rentalCarID)) {
                rentalCarIDs.add(rentalCarID);
            }
        }
        return rentalCarIDs.toArray(new Long[0]);
    }


}




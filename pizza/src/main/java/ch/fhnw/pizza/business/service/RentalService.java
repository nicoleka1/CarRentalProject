package ch.fhnw.pizza.business.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.Menu;
import ch.fhnw.pizza.data.domain.Pizza;
import ch.fhnw.pizza.data.domain.Rental;
import ch.fhnw.pizza.data.repository.RentalRepository;

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

    // Returns the number of rented days for a given rental car ID
    public int getRentedDaysByCarId(Long carId) {
        List<Rental> rentalList = rentalRepository.findAll();
        int rentedDays = 0;
        
        for (Rental rental : rentalList) {
            if (rental.getRentalCarId().equals(carId)) {
                LocalDate startDate = rental.getRentalStartDate();
                LocalDate endDate = rental.getRentalEndDate();
                rentedDays += ChronoUnit.DAYS.between(startDate, endDate) + 1;
            }
        }
        
        return rentedDays;
    }
    
    // Returns the number of rented days for all cars
    public int[] getRentedDaysByAllCars() {
        List<Rental> rentalList = rentalRepository.findAll();
        List<Long> rentalCarIDs = new ArrayList<>();
        
        for (Rental rental : rentalList) {
            Long rentalCarID = rental.getRentalCarId();
            if (!rentalCarIDs.contains(rentalCarID)) {
                rentalCarIDs.add(rentalCarID);
            }
        }
        
        int[] rentedDaysByAllCars = new int[rentalCarIDs.size()];
        for (int i = 0; i < rentalCarIDs.size(); i++) {
            rentedDaysByAllCars[i] = getRentedDaysByCarId(rentalCarIDs.get(i));
        }
        
        return rentedDaysByAllCars;
    }

}




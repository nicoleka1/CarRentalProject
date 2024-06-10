package ch.fhnw.pizza.business.service;

import java.sql.Date;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Rental findRentalByrentalID(Long id) {
        try {
            Rental rental = rentalRepository.findById(id).get();
            return rental;
        } catch (Exception e) {
            throw new RuntimeException("Rental with id " + id + " not found");
        }
    }

    public List<Rental> getAllRentals() {
        List<Rental> rentalList = rentalRepository.findAll();
        return rentalList;
    }

    public Rental addRental(Rental rental) throws Exception {
        return rentalRepository.save(rental);
    }

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

    public void deleteRental(Long id) throws Exception {
        if(rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else
            throw new Exception("Rental with id " + id + " does not exist");
    }

    public List<Rental> getAvailableRentals(ChronoLocalDate startDate, ChronoLocalDate endDate) {
        List<Rental> availableRentals = new ArrayList<>();
        List<Rental> allRentals = rentalRepository.findAll();
        
        for (Rental rental : allRentals) {
            if (rental.getRentalStartDate().compareTo(endDate) <= 0 && rental.getRentalEndDate().compareTo(startDate) >= 0) {
                // Rental overlaps with the given time period
                continue;
            }
            availableRentals.add(rental);
        }
        
        return availableRentals;
    }


 /* //Business Logic to get current offer according to the location of the user requesting the menu
    private String getCurrentOffer(String location) {
        String currentOffer = "No special offer for your location. Do check back again.";
        if("Basel".equalsIgnoreCase(location))
            currentOffer = "10% off on all large pizzas!!!";
        else if("Brugg".equalsIgnoreCase(location))
            currentOffer = "Two for the price of One on all small pizzas!!!";
        return currentOffer;
    }

    public Rental getRentalByLocation(String location) {
        String currentBooking = getCurrentBooking(location);
        List<Pizza> pizzaList = getAllPizzas();
        Menu menu = new Menu();
        menu.setPizzaList(pizzaList);
        menu.setCurrentOffer(currentOffer);
        return menu;
    }
   

    public Menu getMenuByLocation(String location) {
        String currentOffer = getCurrentOffer(location);
        List<Pizza> pizzaList = getAllPizzas();
        Menu menu = new Menu();
        menu.setPizzaList(pizzaList);
        menu.setCurrentOffer(currentOffer);
        return menu;
    }

    */
        
}

package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.fhnw.pizza.data.domain.Rental;
import ch.fhnw.pizza.data.repository.RentalRepository;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Rental findRentalByrentalAdminID(Long id) {
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
            if(rental.getRentalStartDate() != null)
                rentalToUpdate.setRentalStartDate(rental.getRentalStartDate());
            if(rental.getRentalEndDate() != null)
                rentalToUpdate.setRentalEndDate(rental.getRentalEndDate());
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

 /* //Business Logic to get current offer according to the location of the user requesting the menu
    private String getCurrentOffer(String location) {
        String currentOffer = "No special offer for your location. Do check back again.";
        if("Basel".equalsIgnoreCase(location))
            currentOffer = "10% off on all large pizzas!!!";
        else if("Brugg".equalsIgnoreCase(location))
            currentOffer = "Two for the price of One on all small pizzas!!!";
        return currentOffer;
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

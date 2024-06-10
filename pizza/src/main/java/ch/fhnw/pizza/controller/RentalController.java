package ch.fhnw.pizza.controller;

import ch.fhnw.pizza.business.service.RentalService;
import ch.fhnw.pizza.data.domain.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path="/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;
    @GetMapping(path="/", produces = "application/json")
    public List<Rental> getRentalList() {
        List<Rental> rentalList = rentalService.getAllRentals();

        return rentalList;
    }

  @PostMapping(path="/", consumes="application/json", produces = "application/json")
    public ResponseEntity addRental(@RequestBody Rental rental) {
        try{
            rental = rentalService.addRental(rental);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rental already exists with given name");
        }
        return ResponseEntity.ok(rental);
        
    }


    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity getRental(@PathVariable Long id) {

        try{
            Rental rental = rentalService.findRentalByrentalID(id);
            return ResponseEntity.ok(rental);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rental found with given id");
        }
    }

   /*@GetMapping(path = "/available-rentals", produces = "application/json")
    public List<Rental> getAvailableRentals(@RequestParam("startDate") ChronoLocalDate startDate, @RequestParam("endDate") ChronoLocalDate endDate) {
        List<Rental> rentalList = rentalService.getAllRentals();
        List<Rental> availableRentals = new ArrayList<>();

        for (Rental rental : rentalList) {
            if ((rental.getRentalStartDate().isAfter(startDate) || rental.getRentalStartDate().isEqual(startDate)) &&
                (rental.getRentalEndDate().isBefore(endDate) || rental.getRentalEndDate().isEqual(endDate))) {
                availableRentals.add(rental);
            }
        }
        return availableRentals;
    }

    */

    @GetMapping(path = "/available-rentals?startDate={startDate}&endDate={endDate}", produces = "application/json")
    public ResponseEntity getAvailableRentals(@RequestParam("startDate") ChronoLocalDate startDate, @RequestParam("endDate") ChronoLocalDate endDate) {
        try {
            List<Rental> rentalList = rentalService.getAllRentals();
            List<Rental> availableRentals = new ArrayList<>();

            for (Rental rental : rentalList) {
                if ((rental.getRentalStartDate().isAfter(startDate) || rental.getRentalStartDate().isEqual(startDate)) &&
                    (rental.getRentalEndDate().isBefore(endDate) || rental.getRentalEndDate().isEqual(endDate))) {
                    availableRentals.add(rental);
                }
            }
            return ResponseEntity.ok(availableRentals);
        } catch (Exception e) {
            // Log the exception message
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateRental(@PathVariable Long id, @RequestBody Rental rental) {
        try{
            rental = rentalService.updateRental(id, rental);
            return ResponseEntity.ok(rental);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No rental found with given id");
        }
    }


    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteRental(@PathVariable Long id) {
        try{
            rentalService.deleteRental(id);
            return ResponseEntity.ok("Rental with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental not found");
        }
    }


/*
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<Rental> getRental(@RequestParam String location) {
       Rental rental = rentalService.getRentalByLocation(location);
        return ResponseEntity.ok(rental);      
    }
    
    */

 
    /*@GetMapping(path="", produces = "application/json")
        public ResponseEntity<Menu> getMenu(@RequestParam String location) {
        Menu menu = menuService.getMenuByLocation(location);
        return ResponseEntity.ok(menu);      
    } */
   
}

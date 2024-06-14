package ch.fhnw.pizza.controller;

import ch.fhnw.pizza.business.service.RentalService;
import ch.fhnw.pizza.data.domain.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;


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
/*    @GetMapping(path="/available-rentals", produces = "application/json")
    public List<Rental> getAvailableRentals(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        List<Rental> rentalList = rentalService.getAvailableRentals(startDate, endDate);
        return rentalList;
    }*/

    @GetMapping(path="/available-rentals", produces = "application/json")
    public List<Rental> getUnailableRentals(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return rentalService.getUnavailableRentals(startDate, endDate);
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
   
}

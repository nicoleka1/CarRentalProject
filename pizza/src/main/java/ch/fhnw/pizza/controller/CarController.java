package ch.fhnw.pizza.controller;

import ch.fhnw.pizza.business.service.CarService;
import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.business.service.RentalService;
import ch.fhnw.pizza.data.domain.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="/cars")
public class CarController {

    @Autowired
    private CarService carService;
    
    @Autowired
    private RentalService rentalService;
    @GetMapping(path="/", produces = "application/json")
    public List<Car> getCarList() {
        List<Car> carList = carService.getAllCars();

        return carList;
    }

    @PostMapping(path="/", consumes="application/json", produces = "application/json")
    public ResponseEntity addCar(@RequestBody Car car) {
        try{
            car = carService.addCar(car);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Car already exists with given name");
        }
        return ResponseEntity.ok(car);
        
    }


    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity getCar(@PathVariable Long id) {

        try{
            Car car = carService.findCarByCarID(id);
            return ResponseEntity.ok(car);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car found with given id");
        }
    }


    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody Car car) {
        try{
            car = carService.updateCar(id, car);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No car found with given id");
        }
    }


    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        try{
            carService.deleteCar(id);
            return ResponseEntity.ok("Car with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
    }

    @GetMapping(path="/available-cars", produces = "application/json")
    public List<Car> getAvailableCars(@RequestParam("startDate") LocalDate startDate,
                                      @RequestParam("endDate") LocalDate endDate) {
        Long[] unavailableCarIDs = rentalService.getUnavailableRentalCarIDs(startDate, endDate);
        List<Car> allCars = carService.getAllCars();
        List<Car> availableCars = new ArrayList<>();
        System.out.println("startDate: " + startDate);
        System.out.println("endDate: " + endDate);

        for (Car car : allCars) {
            if (!Arrays.asList(unavailableCarIDs).contains(car.getCarId())) {
                availableCars.add(car);
            }
        }
        System.out.println("availableCars: " + availableCars);
        return availableCars;
    }

    @GetMapping(path="/rented-days", produces = "application/json")
    public List<Car> getCarDetailsWithRentedDays(){
        List<Car> carList = carService.getCarDetailsWithRentedDays();
        return carList;
    }
    


    

    /*@GetMapping(path="/rented-days", produces = "application/json")
    public int[] getRentedDaysByAllCars() {
        List<Rental> rentalList = rentalService.getAllRentals();
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
    }*/



 

}

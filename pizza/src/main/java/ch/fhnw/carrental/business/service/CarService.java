package ch.fhnw.carrental.business.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ch.fhnw.carrental.data.domain.Car;
import ch.fhnw.carrental.data.domain.Rental;
import ch.fhnw.carrental.business.service.RentalService;
import ch.fhnw.carrental.data.repository.RentalRepository;

import ch.fhnw.carrental.data.repository.CarRepository;



@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalService rentalService;
    
    public Car findCarByCarID(Long id) {
        try {
            Car car = carRepository.findById(id).get();
            return car;
        } catch (Exception e) {
            throw new RuntimeException("Car with id " + id + " not found");
        }
    }

    public List<Car> getAllCars() {
        List<Car> carList = carRepository.findAll();
        return carList;
    }

    public Car addCar(Car car) throws Exception {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car car) throws Exception {
        Car carToUpdate = carRepository.findById(id).get();
        if(carToUpdate != null) {
            if(car.getCarBrand() != null)
                carToUpdate.setCarBrand(car.getCarBrand());
            if(car.getCarModel() != null)
                carToUpdate.setCarModel(car.getCarModel());
            if(car.getCarYear() > 0)
                carToUpdate.setCarYear(car.getCarYear());
            if(car.getCarColor() != null)
                carToUpdate.setCarColor(car.getCarColor());
            if(car.getCarLicencePlate() != null)
                carToUpdate.setCarLicencePlate(car.getCarLicencePlate());
            if(car.getCarRentalRate() != null)
                carToUpdate.setCarRentalRate(car.getCarRentalRate());
            return carRepository.save(carToUpdate);
        }
        throw new Exception("Car with id " + id + " does not exist");
    }

    public void deleteCar(Long id) throws Exception {
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else
            throw new Exception("Car with id " + id + " does not exist");
    }

    public List<Car> getAvailableCars(LocalDate startDate,LocalDate endDate) {

        Long[] unavailableCarIDs = rentalService.getUnavailableRentalCarIDs(startDate, endDate);
        List<Car> allCars = getAllCars();
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


    public List<Car> getCarDetailsWithRentedDays() {
        List<Car> carList = carRepository.findAll();
        List<Car> carDetailsList = new ArrayList<>();
        List<Rental> rentalList = rentalRepository.findAll();

        for (Car car : carList) {
            Long carID = car.getCarId();
            int rentedDays = 0;

            for (Rental rental : rentalList) {
                Long rentalCarID = rental.getRentalCarId();
                LocalDate startDate = rental.getRentalStartDate();
                LocalDate endDate = rental.getRentalEndDate();

                if (carID.equals(rentalCarID)) {
                    rentedDays += ChronoUnit.DAYS.between(startDate, endDate) + 1;
                }
            }

            car.setCarRentalCount(rentedDays);
            carDetailsList.add(car);
        }

        return carDetailsList;
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

package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.data.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

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

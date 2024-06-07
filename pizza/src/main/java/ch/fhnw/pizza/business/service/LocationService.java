package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.fhnw.pizza.data.domain.Location;
import ch.fhnw.pizza.data.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;


    public Location findLocationBylocationCity(String locationCity) {
        Location location = locationRepository.findLocationBylocationCity(locationCity);
        return location;
    }


    public Location findLocationById(Long id) {
        try {
            Location location = locationRepository.findById(id).get();
            return location;
        } catch (Exception e) {
            throw new RuntimeException("Location with id " + id + " not found");
        }
    }

    public List<Location> getAllLocation() {
        List<Location> locationList = locationRepository.findAll();
        return locationList;
    }

    public Location addLocation(Location location) throws Exception {
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location location) throws Exception {
        Location locationToUpdate = locationRepository.findById(id).get();
        if(locationToUpdate != null) {
            if(location.getLocationCity() != null)
                locationToUpdate.setLocationCity(location.getLocationCity());
            if(location.getLocationAddress() != null)
                locationToUpdate.setLocationAddress(location.getLocationAddress());
            return locationRepository.save(locationToUpdate);
        }
        throw new Exception("Location with id " + id + " does not exist");
    }

    public void deleteLocation(Long id) throws Exception {
        if(locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
        } else
            throw new Exception("Location with id " + id + " does not exist");
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

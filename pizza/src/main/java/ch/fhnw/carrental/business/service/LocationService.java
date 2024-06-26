package ch.fhnw.carrental.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.fhnw.carrental.data.domain.Location;
import ch.fhnw.carrental.data.repository.LocationRepository;

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
            if(location.getLocationName() != null)
                locationToUpdate.setLocationName(location.getLocationName());
            if(location.getLocationAddress() != null)
                locationToUpdate.setLocationAddress(location.getLocationAddress());
            if(location.getLocationCity() != null)
                locationToUpdate.setLocationCity(location.getLocationCity());
            if(location.getLocationState() != null)
                locationToUpdate.setLocationState(location.getLocationState());
            if(location.getLocationZipCode()!= null)
                locationToUpdate.setLocationZipCode(location.getLocationZipCode());
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

        
}

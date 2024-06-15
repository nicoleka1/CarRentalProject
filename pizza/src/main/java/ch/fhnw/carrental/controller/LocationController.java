package ch.fhnw.carrental.controller;

import ch.fhnw.carrental.business.service.LocationService;
import ch.fhnw.carrental.data.domain.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @GetMapping(path="/", produces = "application/json")
    public List<Location> getLocationList() {
        List<Location> locationList = locationService.getAllLocation();
        return locationList;
    }

    @PostMapping(path="/", consumes="application/json", produces = "application/json")
    public ResponseEntity addLocation(@RequestBody Location location) {
        try{
            location = locationService.addLocation(location);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location already exists with given name");
        }
        return ResponseEntity.ok(location);
        
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity getLocation(@PathVariable Long id) {

        try{
            Location location = locationService.findLocationById(id);
            return ResponseEntity.ok(location);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No location found with given id");
        }
    }

    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateLocation(@PathVariable Long id, @RequestBody Location location) {
        try{
            location = locationService.updateLocation(id, location);
            return ResponseEntity.ok(location);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No location found with given id");
        }
    }


    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        try{
            locationService.deleteLocation(id);
            return ResponseEntity.ok("Location with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }
    }


    
}

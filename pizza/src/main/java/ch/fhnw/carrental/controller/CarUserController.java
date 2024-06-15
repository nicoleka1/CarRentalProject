package ch.fhnw.carrental.controller;

import ch.fhnw.carrental.business.service.CarUserService;
import ch.fhnw.carrental.data.domain.CarUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="/caruser")
public class CarUserController {

    @Autowired
    private CarUserService carUserService;
    @GetMapping(path="/", produces = "application/json")
    public List<CarUser> getCarUserList() {
        List<CarUser> carUserList = carUserService.getAllCarUsers();
        return carUserList;
    }

    @PostMapping(path="/", consumes="application/json", produces = "application/json")
    public ResponseEntity addCarUser(@RequestBody CarUser carUser) {
        try{
            carUser = carUserService.addCarUser(carUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("carUser already exists with given name");
        }
        return ResponseEntity.ok(carUser);
        
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity getCarUser(@PathVariable Long id) {

        try{
            CarUser carUser = carUserService.findCarUserById(id);
            return ResponseEntity.ok(carUser);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No carUser found with given id");
        }
    }

    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateCarUser(@PathVariable Long id, @RequestBody CarUser carUser) {
        try{
            carUser = carUserService.updateCarUser(id, carUser);
            return ResponseEntity.ok(carUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No carUser found with given id");
        }
    }


    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteCarUser(@PathVariable Long id) {
        try{
            carUserService.deleteCarUser(id);
            return ResponseEntity.ok("CarUser with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarUser not found");
        }
    }


    
}

package ch.fhnw.pizza.controller;

import ch.fhnw.pizza.business.service.AdminService;
import ch.fhnw.pizza.data.domain.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @GetMapping(path="/", produces = "application/json")
    public List<Admin> getAdminList() {
        List<Admin> adminList = adminService.getAllAdmins();

        return adminList;
    }

    @PostMapping(path="/", consumes="application/json", produces = "application/json")
    public ResponseEntity addAdmin(@RequestBody Admin admin) {
        try{
            admin = adminService.addAdmin(admin);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Admin already exists with given name");
        }
        return ResponseEntity.ok(admin);
        
    }


 /*   @GetMapping(path="/{id}", produces = "application/json")
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


 
    //@GetMapping(path="", produces = "application/json")
    //public ResponseEntity<Menu> getMenu(@RequestParam String location) {
    //    Menu menu = menuService.getMenuByLocation(location);
    //    return ResponseEntity.ok(menu);      
    //}

     */
    
}

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

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity getAdmin(@PathVariable Long id) {

        try{
            Admin admin = adminService.findAdminById(id);
            return ResponseEntity.ok(admin);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No admin found with given id");
        }
    }

    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        try{
            admin = adminService.updateAdmin(id, admin);
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No admin found with given id");
        }
    }


    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        try{
            adminService.deleteAdmin(id);
            return ResponseEntity.ok("Admin with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    }


    
}

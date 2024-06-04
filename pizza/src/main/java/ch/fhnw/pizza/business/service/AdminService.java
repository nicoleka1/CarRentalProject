package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.fhnw.pizza.data.domain.Admin;
import ch.fhnw.pizza.data.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public Admin findAdminByAdminEmail(String adminEmail) {
        Admin admin = adminRepository.findAdminByAdminEmail(adminEmail);
        return admin;
    }


    public Admin findAdminById(Long id) {
        try {
            Admin admin = adminRepository.findById(id).get();
            return admin;
        } catch (Exception e) {
            throw new RuntimeException("Admin with id " + id + " not found");
        }
    }

    public List<Admin> getAllAdmins() {
        List<Admin> adminList = adminRepository.findAll();
        return adminList;
    }

    public Admin addAdmin(Admin admin) throws Exception {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin admin) throws Exception {
        Admin adminToUpdate = adminRepository.findById(id).get();
        if(adminToUpdate != null) {
            if(admin.getAdminEmail() != null)
                adminToUpdate.setAdminEmail(admin.getAdminEmail());
            if(admin.getAdminName() != null)
                adminToUpdate.setAdminName(admin.getAdminName());
            return adminRepository.save(adminToUpdate);
        }
        throw new Exception("Admin with id " + id + " does not exist");
    }

    public void deleteAdmin(Long id) throws Exception {
        if(adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        } else
            throw new Exception("Admin with id " + id + " does not exist");
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

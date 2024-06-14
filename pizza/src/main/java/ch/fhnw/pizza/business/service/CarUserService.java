package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.fhnw.pizza.data.domain.CarUser;
import ch.fhnw.pizza.data.repository.CarUserRepository;

@Service
public class CarUserService {

    @Autowired
    private CarUserRepository carUserRepository;


    public CarUser findCarUserBycarUserEmail(String carUserEmail) {
        CarUser carUser = carUserRepository.findCarUserBycarUserEmail(carUserEmail);
        return carUser;
    }


    public CarUser findCarUserById(Long id) {
        try {
            CarUser carUser = carUserRepository.findById(id).get();
            return carUser;
        } catch (Exception e) {
            throw new RuntimeException("CarUser with id " + id + " not found");
        }
    }

    public List<CarUser> getAllCarUsers() {
        List<CarUser> carUserList = carUserRepository.findAll();
        return carUserList;
    }

    public CarUser addCarUser(CarUser carUser) throws Exception {
        return carUserRepository.save(carUser);
    }

    public CarUser updateCarUser(Long id, CarUser carUser) throws Exception {
        CarUser carUserToUpdate = carUserRepository.findById(id).get();
        if(carUserToUpdate != null) {
            if(carUser.getCarUserName() != null)
            carUserToUpdate.setCarUserName(carUser.getCarUserName());
            if(carUser.getCarUserEmail() != null)
            carUserToUpdate.setCarUserEmail(carUser.getCarUserEmail());
            if(carUser.getCarUserPassword() != null)
            carUserToUpdate.setCarUserPassword(carUser.getCarUserPassword());
            if(carUser.getCarUserAddress() != null)
            carUserToUpdate.setCarUserAddress(carUser.getCarUserAddress());
            if(carUser.getCarUserPhone() != null)
            carUserToUpdate.setCarUserPhone(carUser.getCarUserPhone());
            return carUserRepository.save(carUserToUpdate);
        }
        throw new Exception("User with id " + id + " does not exist");
    }

    public void deleteCarUser(Long id) throws Exception {
        if(carUserRepository.existsById(id)) {
            carUserRepository.deleteById(id);
        } else
            throw new Exception("CarUser with id " + id + " does not exist");
    }

        
}

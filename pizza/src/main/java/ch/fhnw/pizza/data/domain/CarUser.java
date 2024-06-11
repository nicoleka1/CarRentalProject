package ch.fhnw.pizza.data.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "caruser")
public class CarUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "car_user_id", nullable = false)
    private Long carUserID;

    @Column(name = "car_user_name")
    private String carUserName;

    @Column(name = "car_user_surname")
    private String carUserSurname;

    @Column(name = "car_user_email")
    private String carUserEmail;

    @Column(name = "car_user_password")
    private String carUserPassword;

    @Column(name = "car_user_address")
    private String carUserAddress;

    @Column(name = "car_user_phone")
    private String carUserPhone;

    public Long getCarUserID() {
        return carUserID;
    }

    public void setCarUserID(Long carUserID) {
        this.carUserID = carUserID;
    }

    public String getCarUserName() {
        return carUserName;
    }

    public void setCarUserName(String carUserName) {
        this.carUserName = carUserName;
    }

    public String getCarUserSurname() {
        return carUserSurname;
    }

    public void setCarUserSurname(String carUserSurname) {
        this.carUserSurname = carUserSurname;
    }

    public String getCarUserEmail() {
        return carUserEmail;
    }

    public void setCarUserEmail(String carUserEmail) {
        this.carUserEmail = carUserEmail;
    }

    public String getCarUserPassword() {
        return carUserPassword;
    }

    public void setCarUserPassword(String carUserPassword) {
        this.carUserPassword = carUserPassword;
    }

    public String getCarUserAddress() {
        return carUserAddress;
    }

    public void setCarUserAddress(String carUserAddress) {
        this.carUserAddress = carUserAddress;
    }

    public String getCarUserPhone() {
        return carUserPhone;
    }

    public void setCarUserPhone(String carUserPhone) {
        this.carUserPhone = carUserPhone;
    }

 





    
}

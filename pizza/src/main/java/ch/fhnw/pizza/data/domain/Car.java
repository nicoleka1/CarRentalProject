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
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "car_id", nullable = false)
    private Long carID;

    @Column(name = "car_brand")
    private String carBrand;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_year")
    private int carYear;

    @Column(name = "car_color")
    private String carColor;

    @Column(name = "car_licence_plate")
    private String carLicencePlate;

    @Column(name = "car_rental_rate")
    private Long carRentalRate;

    @Column(name = "car_availability")
    private String carAvailability;

    @ManyToOne
    private Menu menu;

    public Long getCarId() {
        return carID;
    }

    public void setId(Long carID) {
        this.carID = carID;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    } 
    
}

package ch.fhnw.pizza.data.domain;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "rental_id", nullable = false)
    private Long rentalID;

    @Column(name = "rental_admin_id")
    private Long rentalAdminID;

    @Column(name = "rental_car_id")
    private Long rentalCarID;

    @Column(name = "rental_start_date")
    private LocalDate rentalStartDate;

    @Column(name = "rental_end_date")
    private LocalDate rentalEndDate;

    @Column(name = "rental_total_cost")
    private Long rentalTotalCost;

    @Column(name = "rental_status")
    private String rentalStatus;

    public Long getRentalId() {
        return rentalID;
    }

    public void setId(Long rentalID) {
        this.rentalID = rentalID;
    }

    public Long getrentalID() {
        return rentalID;
    }

    public void setRentalAdminId(long i) {
        this.rentalAdminID = i;
    }

    public Long getRentalCarId() {
        return rentalCarID;
    }

    public void setRentalCarId(long i) {
        this.rentalCarID = i;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }
 
    public void setRentalStartDate(LocalDate date) {
        this.rentalStartDate = date;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(LocalDate date) {
        this.rentalEndDate = date;
    }

    public Long getRentalTotalCost() {
        return rentalTotalCost;
    }

    public void setRentalTotalCost(Long rentalTotalCost) {
        this.rentalTotalCost = rentalTotalCost;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

}

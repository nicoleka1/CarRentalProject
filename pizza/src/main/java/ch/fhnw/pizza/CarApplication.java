package ch.fhnw.pizza;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.CarService;
import ch.fhnw.pizza.business.service.RentalService;
import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.business.service.AdminService;
import ch.fhnw.pizza.data.domain.Rental;
import ch.fhnw.pizza.business.service.RentalService;
import ch.fhnw.pizza.data.domain.Admin;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class CarApplication {

	@Autowired
	private CarService carService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private RentalService rentalService;

	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}
	

	// Use this method to initialize placeholder data without using Postman
	// If you are persisting data in a file (see application.properties), initializing data that already exists will cause an error during starting the application
	// To resolve the error, delete the file and restart the application

	// Default values set for the car object for testing purposes
	@PostConstruct
	private void initPlaceholderData() throws Exception {
		Car car = new Car();
		car.setCarBrand("VW");
		car.setCarModel("Beetle");
		car.setCarYear(2015);
		car.setCarColor("Red");
		car.setCarLicencePlate("AG 123 456");
		car.setCarRentalRate(100L);
		car.setCarAvailability("Available");
		carService.addCar(car);
		
		Car car2 = new Car();
		car2.setCarBrand("Audi");
		car2.setCarModel("A3");
		car2.setCarYear(2018);
		car2.setCarColor("Black");
		car2.setCarLicencePlate("AG 789 123");
		car2.setCarRentalRate(150L);
		car2.setCarAvailability("Available");
		carService.addCar(car2);

		Car car3 = new Car();
		car3.setCarBrand("BMW");
		car3.setCarModel("X5");
		car3.setCarYear(2020);
		car3.setCarColor("White");
		car3.setCarLicencePlate("AG 456 789");
		car3.setCarRentalRate(200L);
		car3.setCarAvailability("Available");
		carService.addCar(car3);


		Admin admin1 = new Admin();
		admin1.setAdminName("John Doe");
		admin1.setAdminEmail("john.doe@example.com");
		adminService.addAdmin(admin1);


		Rental rental1 = new Rental();
		rental1.setRentalCarId(0);
		rental1.setRentalAdminId(0);
		rental1.setRentalStartDate(LocalDate.of(2021, 10, 1));
		rental1.setRentalEndDate(LocalDate.of(2021, 10, 5));
		rental1.setRentalTotalCost(1000L);
		rentalService.addRental(rental1);


	}


}

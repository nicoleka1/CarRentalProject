package ch.fhnw.pizza;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.AdminService;
import ch.fhnw.pizza.business.service.CarService;
import ch.fhnw.pizza.business.service.LocationService;
import ch.fhnw.pizza.business.service.RentalService;
import ch.fhnw.pizza.business.service.CarUserService;
import ch.fhnw.pizza.data.domain.Admin;
import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.data.domain.Location;
import ch.fhnw.pizza.data.domain.Rental;
import ch.fhnw.pizza.data.domain.CarUser;
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
	private CarUserService carUserService;

	@Autowired
	private RentalService rentalService;

	@Autowired
	private LocationService locationService;

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
		carService.addCar(car);
		
		Car car2 = new Car();
		car2.setCarBrand("Audi");
		car2.setCarModel("A3");
		car2.setCarYear(2018);
		car2.setCarColor("Black");
		car2.setCarLicencePlate("AG 789 123");
		car2.setCarRentalRate(150L);
		carService.addCar(car2);

		Car car3 = new Car();
		car3.setCarBrand("BMW");
		car3.setCarModel("X5");
		car3.setCarYear(2020);
		car3.setCarColor("White");
		car3.setCarLicencePlate("AG 456 789");
		car3.setCarRentalRate(200L);
		carService.addCar(car3);
		
		Car car4 = new Car();
		car4.setCarBrand("Mercedes");
		car4.setCarModel("C-Class");
		car4.setCarYear(2019);
		car4.setCarColor("Silver");
		car4.setCarLicencePlate("AG 987 654");
		car4.setCarRentalRate(180L);
		carService.addCar(car4);

		Car car5 = new Car();
		car5.setCarBrand("Toyota");
		car5.setCarModel("Camry");
		car5.setCarYear(2020);
		car5.setCarColor("Blue");
		car5.setCarLicencePlate("AG 654 321");
		car5.setCarRentalRate(160L);
		carService.addCar(car5);

		Car car6 = new Car();
		car6.setCarBrand("Ford");
		car6.setCarModel("Mustang");
		car6.setCarYear(2017);
		car6.setCarColor("Yellow");
		car6.setCarLicencePlate("AG 321 654");
		car6.setCarRentalRate(200L);
		carService.addCar(car6);

		Car car7 = new Car();
		car7.setCarBrand("Honda");
		car7.setCarModel("Accord");
		car7.setCarYear(2018);
		car7.setCarColor("Gray");
		car7.setCarLicencePlate("AG 654 987");
		car7.setCarRentalRate(150L);
		carService.addCar(car7);

		Car car8 = new Car();
		car8.setCarBrand("Nissan");
		car8.setCarModel("Altima");
		car8.setCarYear(2016);
		car8.setCarColor("Black");
		car8.setCarLicencePlate("AG 789 654");
		car8.setCarRentalRate(140L);
		carService.addCar(car8);

		Car car9 = new Car();
		car9.setCarBrand("Chevrolet");
		car9.setCarModel("Cruze");
		car9.setCarYear(2015);
		car9.setCarColor("Red");
		car9.setCarLicencePlate("AG 456 321");
		car9.setCarRentalRate(120L);
		carService.addCar(car9);
		
		Car car10 = new Car();
		car10.setCarBrand("Ford");
		car10.setCarModel("Focus");
		car10.setCarYear(2017);
		car10.setCarColor("Blue");
		car10.setCarLicencePlate("AG 789 456");
		car10.setCarRentalRate(130L);
		carService.addCar(car10);


		Admin admin1 = new Admin();
		admin1.setAdminName("John Doe");
		admin1.setAdminEmail("john.doe@example.com");
		admin1.setAdminPassword(1234);
		admin1.setAdminAddress("1234 Elm Street");
		admin1.setAdminPhone("123-456-7890");
		adminService.addAdmin(admin1);

		Admin admin2 = new Admin();
		admin2.setAdminName("Alice Hemsworth");
		admin2.setAdminEmail("alice.hemsworth@example.com");
		admin2.setAdminPassword(5678);
		admin2.setAdminAddress("5678 Oak Street");
		admin2.setAdminPhone("098-765-4321");
		adminService.addAdmin(admin2);

		Admin admin3 = new Admin();
		admin3.setAdminName("Bob Smith");
		admin3.setAdminEmail("bob.smith@example.com");
		admin3.setAdminPassword(9876);
		admin3.setAdminAddress("1234 Elm Street");
		admin3.setAdminPhone("123-456-7890");
		adminService.addAdmin(admin3);

		CarUser carUser1 = new CarUser();
		carUser1.setCarUserName("Jane Doe");
		carUser1.setCarUserSurname("Doe");
		carUser1.setCarUserEmail("jane.doe@example.com");
		carUser1.setCarUserPassword("password");
		carUser1.setCarUserAddress("5678 Oak Street");
		carUser1.setCarUserPhone("098-765-4321");
		carUserService.addCarUser(carUser1);

		CarUser carUser2 = new CarUser();
		carUser2.setCarUserName("Alice");
		carUser2.setCarUserSurname("Wonderland");
		carUser2.setCarUserEmail("alice.wonderland@example.com");
		carUser2.setCarUserPassword("password");
		carUser2.setCarUserAddress("1234 Elm Street");
		carUser2.setCarUserPhone("123-456-7890");
		carUserService.addCarUser(carUser2);

		CarUser carUser3 = new CarUser();
		carUser3.setCarUserName("Bob");
		carUser3.setCarUserSurname("Builder");
		carUser3.setCarUserEmail("bob.builder@example.com");
		carUser3.setCarUserPassword("password");
		carUser3.setCarUserAddress("5678 Oak Street");
		carUser3.setCarUserPhone("098-765-4321");
		carUserService.addCarUser(carUser3);	

		Rental rental1 = new Rental();
		rental1.setRentalCarId(4);
		rental1.setRentalUserID(1);
		rental1.setRentalStartDate(LocalDate.of(2021, 10, 1));
		rental1.setRentalEndDate(LocalDate.of(2021, 10, 5));
		rental1.setRentalTotalCost(1000L);
		rentalService.addRental(rental1);

		Rental rental2 = new Rental();
		rental2.setRentalCarId(5);
		rental2.setRentalUserID(1);
		rental2.setRentalStartDate(LocalDate.of(2021, 5, 1));
		rental2.setRentalEndDate(LocalDate.of(2021, 5, 6));
		rental2.setRentalTotalCost(800L);
		rentalService.addRental(rental2);

		Rental rental3 = new Rental();
		rental3.setRentalCarId(5);
		rental3.setRentalUserID(1);
		rental3.setRentalStartDate(LocalDate.of(2021, 5, 5));
		rental3.setRentalEndDate(LocalDate.of(2021, 5, 8));
		rental3.setRentalTotalCost(600L);
		rentalService.addRental(rental3);

		Rental rental4 = new Rental();
		rental4.setRentalCarId(5);
		rental4.setRentalUserID(1);
		rental4.setRentalStartDate(LocalDate.of(2021, 5, 17));
		rental4.setRentalEndDate(LocalDate.of(2021, 5, 17));
		rental4.setRentalTotalCost(200L);
		rentalService.addRental(rental4);


		Location location1 = new Location();
		location1.setLocationName("Zurich");
		location1.setLocationAddress("1234 Elm Street");
		location1.setLocationCity("Zurich");
		location1.setLocationState("ZH");
		location1.setLocationZipCode("8000");
		locationService.addLocation(location1);

		Location location2 = new Location();
		location2.setLocationName("Aargau");
		location2.setLocationAddress("5678 Oak Street");
		location2.setLocationCity("Aarau");
		location2.setLocationState("AG");
		location2.setLocationZipCode("5000");
		locationService.addLocation(location2);
		
	}


}

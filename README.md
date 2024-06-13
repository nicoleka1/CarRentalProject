# Car_Rental_Project_Internet_Technology
Our fundamental concept of this group project is to create a functional Web application to practise and use the knowledge we have learned from the lectures and e-lectures.

#### Contents:
- [Analysis](#analysis)
  - [Scenario Ideation](#scenario-ideation)
  - [User Story Writing](#user-story-writing)
  - [Use Case](#use-case)
- [Design](#design)
  - [Prototype Design](#prototype-design)
  - [Domain Design](#domain-design)
  - [Business Logic](#business-logic)
- [Implementation](#implementation)
  - [Backend Technology](#backend-technology)
  - [Frontend Technology](#frontend-technology)
- [Project Management](#project-management)
  - [Roles](#roles)
  - [Milestones](#milestones)

## Analysis
In our analysis, we specify the following sections to ensure a comprehensive understanding of the requirements and functionalities of our web application. 

### Scenario Ideation

L&N CARRENTAL (Car_Rental_Project) is a Web application allowing the car rental manager (Role: Admin) and the customers (Role: User) to manage car rental operations.

### User Story Writing

1. As an admin, I want to have a Web application so that I can use it on different mobile devices and on desktop computers.
2. As an admin, I want to see a consistent visual appearance so that I can navigate easily, and it looks consistent.
3. As an admin, I want to use list views so that I can explore and read my business data (get).
4. As an admin, I want to update (update) and create (post) views so that I can maintain my business data.
5. As an admin, I want to delete (delete) specific data entries to maintain my business data.
6. As an admin, I want the system to validate my credentials during login to ensure security.
7. As an admin, I want to be notified with error messages if I provide invalid credentials during login.
8. As a user, I want to have a Web applicatioon so that I can use it on different mobile devices and on desktop computers.
9. As a user, I want to see a consistent visual appearance so that I can navigate easily, and it looks consistent.
10. As a user, I want to search for available cars in a specific period of time (business logic).
11. As a user, I want to see the frequency of cars rented (business logic).
12. As a user, I want to see information about the company.
13. As a suer, I want to be able to contact the company.
5. As a user, I want the appication to validate my credentials during login to ensure security.
6. As a user, I want to be notified with error messages if I provide invalid credentials during login.

#### Some stories as more detailed scenarios:

#### Scenario 1: Admin Login
------------------------------------------------------------
Descripiton: The admin logs in to see all the business data.

Steps:
1. Admin navigates to the login screen.
2. Admin enters the credentials (username & password)
3. System verifies the credentials.
4. Admin can navigate to the business data screens.
5. Admin logs out.


#### Scenario 2: Admin Management
------------------------------------------------------------
Description: The admin wants to maintain the DB, such as such as user management, admmin management, car inventory management, location management and rental management. 

Steps: 
1. Admin navigates to the login sceen.
2. Admin enters the credentials (username & password)
3. System verifies the credentials.
4. Admin can navigate int the "AdminSpace" with five management screens: users, admins, cars, locations and rentals.
5. Admin can make CRUD-operations.
6. Admin logs out. 


#### Scenario 3: User Registration
------------------------------------------------------------
Descripiton: The user logs in to see business data.

Steps:
1. User navigates to the login screen.
2. User enters the credentials (username & password)
3. System verifies the credentials.
4. User can navigate in the "UserSpace".
5. User logs out.


#### Scenario 4: Searching for Available Cars
------------------------------------------------------------
Description: A logged in user wants to search for available cars for rental.

Steps: 
1. User navigates to the available cars screen.
2. User picks a start and and end date of the rental.
3. User submits the search request (refresh).
5. Application retrieves and displays a list of available cars matching the search criteria.
6. User logs out.


#### Scenario 5: Frequency of Rental
------------------------------------------------------------
Description: A logged in user want to see the frequency of cars rented.

Steps: 
1. User navigates to the frequency of rental screen.
2. An overview of the frequency of rentals is provided.
3. User logs out.


### Use Case

#### Use Case 1: Admin Login
------------------------------------------------------------
Actors:         Admin, Application

Description:    Allows the admin to log in.

Precondition:   Admin navigates to the login screen.

Postcondition:  Admin is logged in.

Main Flow: 
1. Admin enters credentials (username & password).
2. System validates the credentials.
3. Credentials are correct.

Alternate Flows: 
Invalid input: If the admin provides invalid credentials, system displays error messages.


#### Use Case 2: Admin Management; Add
------------------------------------------------------------
Actors:          Admin, Application

Description:     Allows the admin to add business data. 

Precondition:    Admin is logged in.

Postcondition:   Desired create operations is executed successfully.  

Main Flow:
1. Admin logs 
2. System validates the credentials.
3. Admin navigates to the management page.
4. Admin create, update, delete desired data. 

Alternate Flows: 
Invalid input: If the admin provides invalid information, system displays error messages. 


#### Use Case 3: User Management (Edit User Information)
------------------------------------------------------------
Actors:         Admin, System

Description:    Allows an admin to edit the information of multiple users.

Precondition:   Admin is logged in and has access to user management functionalities.

Postcondition:  User information is successfully updated.

Main Flow:
1. Admin navigates to a screen: users, admins, cars, location or rentals.
2. Admin pushed the add button.
3. Admin is fowarded to the add screen.
4. Admin fills in the field in the form.
5. 
6. Admin selects a user to edit.
7. Admin modifies the user's information (e.g., name, email, address).
8. Admin saves the changes.
 
Alternate Flows: 
Invalid input: If the admin provides invalid information, system displays error messages.


#### Use Case 4: Car Mangement (Edit Car Information)
------------------------------------------------------------
Actors:         Admin, System

Description:    Allows an admin to edit the information of multiple cars.

Precondition:   Admin is logged in and has access to car management functionalities.

Postcondition:  Car information is successfully updated.

Main Flow:
1. Admin navigates to the car management section.
2. Admin selects a car to edit.
3. Admin modifies the car's information (e.g., make, model, rental rate).
4. Admin saves the changes.

Alternate Flows: 
Invalid input: If the admin provides invalid information, system displays error messages.


#### Use Case 5: User Registration
------------------------------------------------------------
Actors:         User, System

Description:    Allows a user to create a new account on the car rental website.

Precondition:   User navigates to the registration page.

Postcondition:  User account is created successfully.

Main Flow:
1. User provides personal information.
2. System validates the information.
3. System creates a new user account.

Alternate Flows: 
Invalid input: If the user provides invalid information, system displays error messages.


#### Use Case 6: Search for Available Cars
------------------------------------------------------------
Actors:         User, System

Description:    Allows a user to search for available cars based on specified criteria.

Precondition:   User is logged in and navigates to the search page.

Postcondition:  User views a list of available cars matching the search criteria.

Main Flow:
1. User specifies search criteria.
2. System retrieves and displays available cars.

Alternate Flows: 
No cars available: If no cars match the search criteria, system displays a message.


#### Use Case 7: Rent a Car
------------------------------------------------------------
Actors:         User, System

Description:    Allows a user to rent a car from the available options.

Precondition:   User has selected a car for rental.

Postcondition:  User completes the rental process and receives confirmation.

Main Flow:
1. User selects a car for rental.
2. User specifies rental details (e.g., rental dates, additional options).
3. User confirms the rental.
4. System processes the rental request.
5. System generates a rental confirmation.
6. User receives a confirmation of the rental.

Alternate Flows: 
None


#### Use Case 8: View Payment Details
------------------------------------------------------------
Actors:         User, System

Description:    Allows a user to view the payment details associated with a rental.

Precondition:   User is logged in and navigates to the rental details page.

Postcondition:  User views the payment details of the rental.

Main Flow:
1. User selects a rental to view details.
2. User navigates to the payment details section.
3. System retrieves and displays the payment details associated with the rental.

Alternate Flows: 
No payment details: If there are no payment details associated with the rental, system displays a message.


#### Use Case 9: View Rental Location Details
------------------------------------------------------------
Actors:         User, System

Description:    Allows a user to view the details of the location where a rental takes place.

Precondition:   User is logged in and navigates to the rental details page.

Postcondition:  User views the location details of the rental.

Main Flow:
1. User selects a rental to view details.
2. User navigates to the location details section.
3. System retrieves and displays the location details where the rental takes place.

Alternate Flows: 
Location details unavailable: If there are no location details associated with the rental, system displays a message.


#### Use Case 10: View Available Cars at Location
------------------------------------------------------------
Actors:         User, System

Description:    Allows a user to view the available cars at a specific rental location.

Precondition:   User is logged in and navigates to the location details page.

Postcondition:  User views the available cars at the location.

Main Flow:
1. User selects a location to view details.
2. User navigates to the available cars section.
3. System retrieves and displays the list of available cars at the location.

Alternate Flows: 
No available cars: If there are no cars available at the location, system displays a message.

### Design
> ***

### Wireframe
> ***

### Prototype Design
> ***

### Domain Design

We created an Entity-Relationship Model in Visual Paradigm.

#### Entities

Admin: Represents the administrators of the website.

User: Represents individuals who interact with the website.

Car: Represents the vehicles available for rental.

Rental Represents a rental transaction.

Payment: Represents a payment transaction associated with a rental.

Location: Represents the rental locations where cars are available.

#### Relationships

##### Admin – User (UserManagement)
One-to-Many relationship indicates that an admin can edit multiple users.

##### Admin – Car (CarManagement)
One-to-Many relationship indicates that an admin can edit multiple cars.

##### User – Rental (MakeRental)
One-to-Many relationship indicating that a user can make multiple rentals, but each rental is associated with only one user.

##### User – Payment (MakePayment)
One-to-Many relationship indicates that a user can make multiple payments, but each payment is made by only one user.

##### Car – Rental (IsRentedFor)
One-to-Many relationship indicating that a car can be rented multiple times, but each rental is associated with only one car.

##### Rental – Payment (IsAssociatedWith)
One-to-One relationship indicating that each rental has exactly one payment associated with it.

##### Rental – Location (TakesPlaceAt)
Many-to-One relationship: Many rentals can take place ate the same rental location, but each rental only takes place at  only one location.

##### Location – Car (IsLocatedAt)
One-to-Many relationship indicating that a location can have multiple cars available for rental, but each car is located at only one location.

#### Model

![](images/EntityRelationshipCarRental2.0.png)

### Business Logic 
> ***

## Implementation
> ***

### Backend Technology
> ***

### Frontend Technology
> ***

## Execution
> ***

## Project Management
> ***

### Roles
- developer: Nicole Kaufmann
- developer: Lea Gauch

### Milestones
1. **Analysis**: Scenario ideation, use case analysis and user story writing. (Submission 2024-04-01)
2. **Prototype Design**: Creation of wireframe and prototype.
3. **Domain Design**: Definition of domain model. (Submission 2024-04-01)
4. **Business Logic and API Design**: Definition of business logic and API.
5. **Data and API Implementation**: Implementation of data access and business logic layers, and API.
6. **Security and Frontend Implementation**: Integration of security framework and frontend realisation.
7. (optional) **Deployment**: Deployment of Web application on cloud infrastructure.


#### Maintainer
- Nicole Kaufmann
- Lea Gauch

#### License
- [Apache License, Version 2.0](blob/master/LICENSE)

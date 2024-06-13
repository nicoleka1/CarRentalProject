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
14. As a user, I want the appication to validate my credentials during login to ensure security.
15. As a user, I want to be notified with error messages if I provide invalid credentials during login.

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


#### Use Case 3: Admin Management; Read
------------------------------------------------------------
Actors:         Admin, Application

Description:    Allows admin to read business data.

Precondition:   Admin is logged in.

Postcondition:  Information is displayed successfully.

Main Flow:
1. Admin navigates to a screen: users, admins, cars, location or rentals.
2. Admin reads the respectitive business data.


#### Use Case 3: Admin Management; Add
------------------------------------------------------------
Actors:         Admin, Application

Description:    Allows admin to add business data.

Precondition:   Admin is logged in.

Postcondition:  Create operation is successfully executed.

Main Flow:
1. Admin navigates to a screen: users, admins, cars, location or rentals.
2. Admin pushed the add button.
3. Admin is fowarded to the add screen.
4. Admin fills in the field in the form.
5. The form is validated.
6. The business data is added with the new entry.

Alternate Flows: 
Invalid input: If the admin provides invalid information, system displays error messages.


#### Use Case 4: Admin Management; Update
------------------------------------------------------------
Actors:         Admin, Application

Description:    Allows admin to update business data.

Precondition:   Admin is logged in.

Postcondition:  Update operation is successfully executed.

Main Flow:
1. Admin navigates to a screen: users, admins, cars, location or rentals.
2. Admin clicks on the row that should be updated.
3. Side panel on the right opens.
6. Admin fills in the field in the form.
7. Admin pushed the update button.
8. The form is validated.
9. The business data is updated with the new entry.

Alternate Flows: 
Invalid input: If the admin provides invalid information, system displays error messages.


#### Use Case 4: Admin Management; Delete
------------------------------------------------------------
Actors:         Admin, Application

Description:    Allows admin to delete business data.

Precondition:   Admin is logged in.

Postcondition:  Delete operation is successfully executed.

Main Flow:
1. Admin navigates to a screen: users, admins, cars, location or rentals.
2. Admin clicks on the row that should be deleted.
3. Side panel on the right opens.
6. Admin pushed the delete button.
7. The form is validated.
8. The business data is deleted.

Alternate Flows: 
-


#### Use Case 5: Search for Available Cars
------------------------------------------------------------
Actors:         User, Application

Description:    Allows a user to search for available cars based on specified period of time.

Precondition:   User is logged in and navigates to the available cars screen.

Postcondition:  User views a list of available cars matching the search criteria.

Main Flow:
1. User specifies search criteria with a start date and an end date.
2. Application retrieves and displays available cars.

Alternate Flows: 
No cars available: If no cars match the search criteria, system displays a message.


#### Use Case 6: Rental Frequency Cars
------------------------------------------------------------
Actors:         User, Application

Description:    Allows a user to see the rentalfrequency of cars.

Precondition:   User is logged in and navigates to the rental frequency screen.

Postcondition:  User views the desired information.

Main Flow:
2. Application retrieves and displays the information.

Alternate Flows: 
No data available: If no data is available, system displays a message.



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

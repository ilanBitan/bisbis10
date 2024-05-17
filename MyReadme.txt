# Restaurant Rating System - Ilan Bitan

## Project Overview

This project implements a backend service for a restaurant rating system using Spring Boot. It allows clients to interact with a RESTful API to manage restaurants, dishes, and ratings. The system supports adding new restaurants, updating existing ones, managing dish details, and adding ratings to restaurants.

## Features

- **Restaurant Management**: Add, update, and delete restaurants.
- **Dish Management**: Add, update, and delete dishes associated with restaurants.
- **Ratings Management**: Users can add ratings to restaurants. The system calculates the average rating automatically based on user submissions.

## Technical Details

### Technologies Used

- **Spring Boot**: Framework for creating stand-alone, production-grade Spring-based applications.
- **Java**: Primary programming language.
- **Hibernate**: ORM tool for data handling.
- **PostgreSQL**: Database for storing all application data.
- **Maven**: Dependency management and project build tool.

### Key Classes and Files

- `Restaurant.java`: Entity representing a restaurant, including its basic attributes and relationships with dishes and ratings.
- `Dish.java`: Entity representing a dish belonging to a particular restaurant.
- `Rating.java`: Entity representing a rating given to a restaurant.
- `RestaurantRepository.java`: Repository interface for accessing restaurant data from the database.
- `DishRepository.java`: Repository interface for accessing dish data from the database.
- `RatingRepository.java`: Repository interface for accessing rating data from the database.
- `RestaurantService.java`: Service class containing business logic for managing restaurants, including average rating calculations.
- `DishService.java`: Service class for managing dish-related operations.
- `RatingService.java`: Service class for managing rating-related operations.
- `RestaurantController.java`: REST controller to handle HTTP requests related to restaurant operations.
- `DishController.java`: REST controller to handle HTTP requests related to dish operations.
- `RatingController.java`: REST controller to handle HTTP requests related to rating operations.

### Setup and Installation

1. **Clone the repository**: Clone the project repository from GitHub.
2. **Database Configuration**: Set up a PostgreSQL database and update the `application.yml` with the correct database connection details.
3. **Build the Project**: Run `mvn clean install` to build the project and resolve dependencies.
4. **Run the Application**: Start the application using Spring Boot's run command or through your IDE. Ensure the application runs on `localhost:8080`.

### API Endpoints

- `POST /restaurants`: Create a new restaurant.
- `PUT /restaurants/{id}`: Update an existing restaurant.
- `DELETE /restaurants/{id}`: Delete a restaurant.
- `POST /restaurants/{restaurantId}/dishes`: Add a new dish to a restaurant.
- `PUT /dishes/{id}`: Update an existing dish.
- `DELETE /dishes/{id}`: Delete a dish.
- `POST /ratings`: Add a rating to a restaurant.

### Additional Configuration

- **Error Handling**: Custom error handling is implemented to manage exceptions and provide meaningful error responses.
- **Security**: Basic authentication is configured to secure API endpoints.

package com.att.tdp.bisbis10.validators;

import com.att.tdp.bisbis10.model.Restaurant;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.List;

@Component
public class RestaurantValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Restaurant.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Restaurant restaurant = (Restaurant) obj;
        validateName(restaurant.getName(), errors);
        validateCuisines(restaurant, errors);  // Updated to use Restaurant
        validateAverageRating(restaurant.getAverageRating(), errors);
    }

    public void validateCuisines(Restaurant restaurant, Errors errors) {
        List<String> cuisines = restaurant.getCuisines();
        if (cuisines == null || cuisines.isEmpty()) {
            errors.rejectValue("cuisines", "cuisines.empty", "At least one cuisine must be specified.");
        }
    }

    private void validateName(String name, Errors errors) {
        if (name == null || name.isEmpty()) {
            errors.rejectValue("name", "name.empty", "Restaurant name must not be empty.");
        }
    }

    private void validateAverageRating(BigDecimal rating, Errors errors) {
        if (rating != null) {
            BigDecimal lowerBound = new BigDecimal("0.0");
            BigDecimal upperBound = new BigDecimal("10.0");
            if (rating.compareTo(lowerBound) < 0 || rating.compareTo(upperBound) > 0) {
                errors.rejectValue("averageRating", "averageRating.invalid", "Average rating must be between 0 and 10.");
            }
        }
    }
}

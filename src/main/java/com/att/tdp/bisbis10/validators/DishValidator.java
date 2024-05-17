package com.att.tdp.bisbis10.validators;

import com.att.tdp.bisbis10.model.Dish;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DishValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Dish.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Dish dish = (Dish) obj;
        validateName(dish.getName(), errors);
        validateDescription(dish.getDescription(), errors);
        validatePrice(dish.getPrice(), errors);
    }

    public void validateUpdate(Object obj, Errors errors) {
        Dish dish = (Dish) obj;
        validateDescription(dish.getDescription(), errors);
        validatePrice(dish.getPrice(), errors);
    }

    private void validateName(String name, Errors errors) {
        if (name == null || name.isEmpty()) {
            errors.rejectValue("name", "name.empty", "Name must not be empty");
        }
    }

    private void validateDescription(String description, Errors errors) {
        if (description == null || description.isEmpty()) {
            errors.rejectValue("description", "description.empty", "Description must not be empty");
        }
    }

    private void validatePrice(double price, Errors errors) {
        if (price <= 0) {
            errors.rejectValue("price", "price.nonPositive", "Price must be larger than zero");
        }
    }
}

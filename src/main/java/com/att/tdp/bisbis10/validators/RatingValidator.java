package com.att.tdp.bisbis10.validators;

import com.att.tdp.bisbis10.model.Rating;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class RatingValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Rating.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Rating rating = (Rating) obj;
        if (rating.getRestaurant() == null || rating.getRestaurant().getId() == null) {
            errors.rejectValue("restaurant", "restaurant.empty", "Restaurant must not be empty");
        }

        validateRatingValue(rating.getRating(), errors);
    }

    private void validateRatingValue(BigDecimal ratingValue, Errors errors) {
        if (ratingValue == null) {
            errors.rejectValue("ratingValue", "ratingValue.null", "Rating value must not be null");
        } else if (ratingValue.compareTo(BigDecimal.ZERO) < 0 || ratingValue.compareTo(new BigDecimal("5")) > 0) {
            errors.rejectValue("ratingValue", "ratingValue.invalid", "Rating must be between 0 and 5");
        }
    }
}

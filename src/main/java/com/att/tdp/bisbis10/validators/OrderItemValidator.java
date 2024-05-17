package com.att.tdp.bisbis10.validators;

import com.att.tdp.bisbis10.model.OrderItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderItem.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        OrderItem orderItem = (OrderItem) obj;

        if (orderItem.getDishId() == null) {
            errors.rejectValue("dishId", "dishId.empty", "DishId must not be empty");
        }

        if (orderItem.getQuantity() <= 0) {
            errors.rejectValue("quantity", "quantity.invalid", "Quantity must be greater than zero");
        }
    }
}

package com.att.tdp.bisbis10.validators;

import com.att.tdp.bisbis10.model.Order;
import com.att.tdp.bisbis10.model.OrderItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Order order = (Order) obj;

        if (order.getRestaurant() == null) {
            errors.rejectValue("restaurant", "restaurant.null", "Restaurant must not be null");
        }

        validateOrderItems(order.getOrderItems(), errors);
    }

    private void validateOrderItems(List<OrderItem> items, Errors errors) {
        if (items == null || items.isEmpty()) {
            errors.rejectValue("orderItems", "orderItems.empty", "Order items must not be empty");
        } else {
            for (OrderItem item : items) {
                if (item == null || item.getDishId() == null) {
                    errors.reject("orderItem.invalid", "Each order item must have a valid dish ID");
                }
            }
        }
    }
}

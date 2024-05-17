package com.att.tdp.bisbis10.DTO;

import java.math.BigDecimal;

public class RatingDTO {
    private Long restaurantId;
    private BigDecimal rating;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}

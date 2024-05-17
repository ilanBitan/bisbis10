package com.att.tdp.bisbis10.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ratings") // Renaming to "ratings" to avoid potential conflicts and maintain consistency
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;

    private BigDecimal ratingValue;

    public Rating() {
        super();
    }

    public Rating(Restaurant restaurant, BigDecimal ratingValue) {
        this.restaurant = restaurant;
        this.ratingValue = ratingValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public BigDecimal getRating() {
        return ratingValue;
    }

    public void setRating(BigDecimal ratingValue) {
        if (ratingValue == null || ratingValue.compareTo(BigDecimal.ZERO) < 0 || ratingValue.compareTo(new BigDecimal("5")) > 0) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.ratingValue = ratingValue;
    }
}

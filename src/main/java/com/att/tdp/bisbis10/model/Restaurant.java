package com.att.tdp.bisbis10.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal averageRating;
    private boolean isKosher;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> cuisines;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Dish> dishes;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Rating> ratings;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Order> orders;

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public Restaurant() {
        ratings = new ArrayList<>();
    }

    public Restaurant(Long id, String name, BigDecimal averageRating, Boolean isKosher, List<String> cuisines) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
        this.ratings = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public BigDecimal getAverageRating() {
        return new BigDecimal(DECIMAL_FORMAT.format(averageRating));
    }

    public void setAverageRating(BigDecimal averageRating) {
        if (averageRating.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Rating cannot be negative");
        }
        this.averageRating = averageRating;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public void setIsKosher(boolean isKosher) {
        this.isKosher = isKosher;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addRating(BigDecimal newRating) {
        Rating rating = new Rating();
        rating.setRating(newRating);
        rating.setRestaurant(this);
        ratings.add(rating);
        calculateAverageRating();
    }

    private void calculateAverageRating() {
        BigDecimal sum = ratings.stream()
                .map(Rating::getRating)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        averageRating = ratings.isEmpty() ? BigDecimal.ZERO : sum.divide(new BigDecimal(ratings.size()), 2, BigDecimal.ROUND_HALF_UP);
    }
}

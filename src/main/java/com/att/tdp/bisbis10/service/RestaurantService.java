package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.model.Restaurant;
import com.att.tdp.bisbis10.projections.IRestaurantProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepo;

    public List<IRestaurantProjection> getAllRestaurants() {
        return restaurantRepo.findAllProjectedBy();
    }

    public List<IRestaurantProjection> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepo.findByCuisinesContaining(cuisine);
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepo.findById(id).orElse(null);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepo.save(restaurant);
    }

    public void updateRestaurant(Long id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepo.findById(id).orElse(null);
        if (existingRestaurant != null) {
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setIsKosher(restaurant.isKosher());
            existingRestaurant.setCuisines(restaurant.getCuisines());
            existingRestaurant.setAverageRating(restaurant.getAverageRating());
            restaurantRepo.save(existingRestaurant);
        }
    }

    public void updateAverageRating(Long restaurantId, BigDecimal newAverageRating) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            restaurant.setAverageRating(newAverageRating);
            restaurantRepo.save(restaurant);
        }
    }

    public boolean existsById(Long id) {
        return restaurantRepo.existsById(id);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }

    public void addRatingToRestaurant(Long restaurantId, BigDecimal rating) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        BigDecimal newAverageRating = calculateNewAverage(restaurant, rating);
        restaurant.setAverageRating(newAverageRating);
        restaurantRepo.save(restaurant);
    }

    private BigDecimal calculateNewAverage(Restaurant restaurant, BigDecimal rating) {
        BigDecimal sumRatings = restaurant.getRatings().stream()
                .map(r -> r.getRating())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(rating);
        BigDecimal count = new BigDecimal(restaurant.getRatings().size() + 1);
        return sumRatings.divide(count, 2, RoundingMode.HALF_UP);
    }
}

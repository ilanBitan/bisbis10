package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.repository.RatingRepository;  // Adjusted for new repository name
import com.att.tdp.bisbis10.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Transactional
    public void addRating(Rating rating) {
        if (rating != null && rating.getRestaurant() != null) {
            ratingRepository.save(rating);
            updateRestaurantRating(rating.getRestaurant().getId());
        }
    }

    private void updateRestaurantRating(Long restaurantId) {
        Double newAverageRating = ratingRepository.calculateAverageRatingByRestaurantId(restaurantId);
        if (newAverageRating != null) {
            BigDecimal averageRating = BigDecimal.valueOf(newAverageRating);
            restaurantService.updateAverageRating(restaurantId, averageRating);
        }
    }

}

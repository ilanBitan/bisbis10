package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.DTO.RatingDTO;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Void> addRating(@RequestBody RatingDTO ratingDTO) {
        restaurantService.addRatingToRestaurant(ratingDTO.getRestaurantId(), ratingDTO.getRating());
        return ResponseEntity.ok().build();
    }
}

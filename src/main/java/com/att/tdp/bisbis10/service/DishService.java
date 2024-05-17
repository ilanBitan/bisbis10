package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.Dish;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    public Optional<Dish> findDishById(Long dishId) {
        return dishRepo.findById(dishId);
    }

    public boolean addDish(Long restaurantId, Dish dish) {
        return restaurantRepo.findById(restaurantId).map(restaurant -> {
            dish.setRestaurant(restaurant);
            dishRepo.save(dish);
            return true;
        }).orElse(false);
    }

    public boolean updateDish(Long restaurantId, Long dishId, Dish updatedDish) {
        return dishRepo.findById(dishId).map(dish -> {
            if (dish.getRestaurant().getId().equals(restaurantId)) {
                dish.setDescription(updatedDish.getDescription());
                dish.setPrice(updatedDish.getPrice());
                dishRepo.save(dish);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public boolean deleteDish(Long restaurantId, Long dishId) {
        Optional<Dish> dish = dishRepo.findById(dishId);
        return dish.map(d -> {
            if (d.getRestaurant().getId().equals(restaurantId)) {
                dishRepo.deleteById(dishId);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        return restaurantRepo.findById(restaurantId)
                .map(restaurant -> dishRepo.findByRestaurant(restaurant))
                .orElse(null);
    }
}

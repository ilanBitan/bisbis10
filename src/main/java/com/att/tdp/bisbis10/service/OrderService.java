package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.DTO.OrderDTO;
import com.att.tdp.bisbis10.model.Order;
import com.att.tdp.bisbis10.model.OrderItem;
import com.att.tdp.bisbis10.model.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Converts an OrderDTO to an Order entity, including fetching the Restaurant by ID.
     * @param orderDTO the DTO to convert.
     * @return the converted Order entity.
     */
    @Transactional
    public Order convertDTOtoOrder(OrderDTO orderDTO) {
        Order order = new Order();
        Restaurant restaurant = restaurantRepository.findById(orderDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with ID: " + orderDTO.getRestaurantId()));
        order.setRestaurant(restaurant);
        order.setOrderItems(orderDTO.getOrderItems().stream().map(item ->
                new OrderItem(item.getDishId(), item.getAmount())).collect(Collectors.toList()));
        return order;
    }

    /**
     * Saves an Order in the repository.
     * @param order the Order to save.
     * @return the saved Order, now with a generated ID.
     */
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders from the database.
     * @return a list of Orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

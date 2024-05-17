package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.DTO.OrderDTO;
import com.att.tdp.bisbis10.model.Order;
import com.att.tdp.bisbis10.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> {
                    OrderDTO dto = new OrderDTO();
                    dto.setOrderId(order.getOrderId().toString());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }
    @PostMapping
    public ResponseEntity<Object> placeOrder(@RequestBody OrderDTO orderDTO) {
        try {
            Order order = orderService.convertDTOtoOrder(orderDTO);
            Order savedOrder = orderService.placeOrder(order);
            return ResponseEntity.ok(new OrderResponseDTO(savedOrder.getOrderId().toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order: " + e.getMessage());
        }
    }

    static class OrderResponseDTO {
        private String orderId;

        public OrderResponseDTO(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}

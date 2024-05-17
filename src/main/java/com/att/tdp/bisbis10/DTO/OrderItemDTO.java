package com.att.tdp.bisbis10.DTO;

public class OrderItemDTO {
    private Long dishId;
    private int amount;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

package org.example.orderservice.dto;

import org.example.orderservice.model.Order;

public class OrderWithUser {
    private Order order;
    private User user;

    public OrderWithUser(Order order, User user) {
        this.order = order;
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
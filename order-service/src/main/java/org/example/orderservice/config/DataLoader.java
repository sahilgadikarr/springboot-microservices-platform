package org.example.orderservice.config;

import org.example.orderservice.model.Order;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private OrderRepository orderRepository;

    public DataLoader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {
        orderRepository.save(new Order(1L, "Laptop", 1200.00));
        orderRepository.save(new Order(2L, "Phone", 800.00));
        orderRepository.save(new Order(1L, "Headphones", 150.00));
    }
}

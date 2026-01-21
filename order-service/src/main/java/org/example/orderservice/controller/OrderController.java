package org.example.orderservice.controller;

import org.example.orderservice.client.UserServiceClient;
import org.example.orderservice.dto.OrderWithUser;
import org.example.orderservice.dto.User;
import org.example.orderservice.model.Order;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.service.UserConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;
    private UserServiceClient userServiceClient;
    private UserConsumerService userConsumerService;

    public OrderController(OrderRepository orderRepository, UserServiceClient userServiceClient,
                           UserConsumerService userConsumerService) {
        this.orderRepository = orderRepository;
        this.userServiceClient = userServiceClient;
        this.userConsumerService = userConsumerService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}/with-user")
    public OrderWithUser getOrderWithUser(@PathVariable(name = "id") Long id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            Optional<User> userOpt = userServiceClient.getUserById(order.getUserId());
            User user = userOpt.orElse(new User());
            return new OrderWithUser(order, user);
        }
        return null;
    }

    @GetMapping("/{id}/{name}/with-user-name")
    public OrderWithUser getOrderWithUserName(@PathVariable(name = "id") Long id, @PathVariable(name = "name") String name) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            Mono<User> monoUser = userConsumerService.getUserByUsername(name);
            User user = monoUser.block();
            if(user == null) {
                return new OrderWithUser(order, new User());
            }
            return new OrderWithUser(order, user);
        }
        return null;
    }
}

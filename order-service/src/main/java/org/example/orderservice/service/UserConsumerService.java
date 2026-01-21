package org.example.orderservice.service;

import org.example.orderservice.dto.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserConsumerService {

    private final WebClient userServiceWebClient;

    public UserConsumerService(@Qualifier("userServiceWebClient") WebClient userServiceWebClient) {
        this.userServiceWebClient = userServiceWebClient;
    }

    public Mono<User> getUserByUsername(String name) {
        return userServiceWebClient.get()
                .uri("/users/name/{name}", name)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(e -> {
                    if (e instanceof org.springframework.web.reactive.function.client.WebClientResponseException.NotFound) {
                        System.err.println("User not found: " + name);
                        return Mono.empty();
                    }
                    System.err.println("Error fetching user: " + e.getMessage());
                    return Mono.error(e);
                });
    }
}
package org.example.userservice.controller;

import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable(name = "id") Long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<User> getUserByName(@PathVariable(name = "name") String name) {
        return userRepository.findByName(name);
    }
}

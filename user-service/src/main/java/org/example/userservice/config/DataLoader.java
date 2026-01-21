package org.example.userservice.config;

import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User("JohnDoe", "john@example.com"));
        userRepository.save(new User("JaneSmith", "jane@example.com"));
        userRepository.save(new User("BobJohnson", "bob@example.com"));
    }
}

package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.user.model.User;
import com.user.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;
    
    @Value("${server.port}")
    private String port;

 @Retry(name="orderService")
 @GetMapping("/orders")
 @CircuitBreaker(
        name = "orderService",
        fallbackMethod = "fallbackOrders"
)
    public String getOrders() {
        return restTemplate.getForObject(
            "http://ORDER-SERVICE/orders",
            String.class
        );
    }

    // Fallback Method
public String fallbackOrders(Exception ex) {

    return "Order Service is DOWN. Try later.";
}


    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "User " + id;
    }

    @GetMapping("/username/{username}")
    public Long getUserIdByUsername(@PathVariable String username) {

    return userRepository.findByName(username)
            .map(User::getId)
            .orElseThrow();
}

    @GetMapping("/admin")
    public String adminAccess() {
        return "Welcome Admin";
    }

    @GetMapping("/test")
    public String test() {
        return "Response from USER-SERVICE Instance running on port: " + port;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
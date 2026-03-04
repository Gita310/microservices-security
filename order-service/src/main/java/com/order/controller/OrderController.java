package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.feign.UserClient;
import com.order.model.Order;
import com.order.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserClient userClient;

    @PostMapping
     public Order createOrder(
        @RequestBody Order order,
        @RequestHeader("X-User") String username) {

    Long userId = userClient.getUserIdByUsername(username);

    order.setUserId(userId);

    return orderRepository.save(order);
}


    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }


    @GetMapping("/user/{id}")
    public List<Order> getUserOrders(@PathVariable Long id) {

        userClient.getUser(id);

        return orderRepository.findByUserId(id);
    }
}
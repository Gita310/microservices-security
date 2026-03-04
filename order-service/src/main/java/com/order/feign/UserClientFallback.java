package com.order.feign;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public Long getUserIdByUsername(String username) {

        System.out.println("User Service DOWN - fallback userId");

        return -1L;
    }

    @Override
    public String getUser(Long id) {

        System.out.println("User Service DOWN - fallback user");

        return "User Service Unavailable";
    }
}
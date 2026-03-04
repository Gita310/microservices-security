package com.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name="USER-SERVICE",
    fallback=UserClientFallback.class
)
public interface UserClient {

    @GetMapping("/users/username/{username}")
    Long getUserIdByUsername(@PathVariable String username);

       @GetMapping("/users/{id}")
    String getUser(@PathVariable Long id);
}
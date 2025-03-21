package com.sb02.mvcdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Map<Long, String> users = Map.of(
        1L, "Alice",
        2L, "Bob",
        3L, "Charlie"
    );

    @GetMapping("/{user_id}")
    public String getUserNameById(@PathVariable("user_id") Long userId) {

        return users.get(userId);
    }

    @GetMapping("/search")
    public List<String> searchUserByName(@RequestParam(name = "keyword", defaultValue = "") String namePrefix) {
        return users.values().stream()
                .filter(s -> s.toLowerCase().startsWith(namePrefix.toLowerCase()))
                .toList();
    }
}

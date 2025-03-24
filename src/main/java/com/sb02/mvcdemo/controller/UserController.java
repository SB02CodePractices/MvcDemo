package com.sb02.mvcdemo.controller;

import com.sb02.mvcdemo.exception.user.UserNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public record User(
            Long id,
            String name
    ) { }

    private static final Map<Long, User> users = Map.of(
        1L, new User(1L, "Alice"),
        2L, new User(2L, "Bob"),
        3L, new User(3L, "Charlie")
    );

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserNameById(
            @PathVariable("user_id") Long userId,
            @RequestHeader("Accept") String acceptHeader
    ) {
        logger.info("Accept header: " + acceptHeader);

        User found = users.get(userId);

        if (found == null) {
            String errMessage = "User with id " + userId + " not found";
            logger.error(errMessage);
            throw new UserNotFound(errMessage);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(found);
    }

    @GetMapping("/search")
    public List<User> searchUserByName(@RequestParam(name = "keyword", defaultValue = "") String namePrefix) {
        return users.values().stream()
                .filter(s -> s.name().toLowerCase().startsWith(namePrefix.toLowerCase()))
                .toList();
    }
}

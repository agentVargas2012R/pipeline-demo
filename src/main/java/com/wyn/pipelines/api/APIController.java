package com.wyn.pipelines.api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wyn.models.User;

@RestController
@RequestMapping("/api/users")
public class APIController {
    private List<User> users = new ArrayList<>();
    private long nextId = 1;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        return user.orElse(null); // Or throw a custom exception if not found
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                updatedUser.setId(id);
                users.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null; // Or throw a custom exception if not found
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        users.removeIf(user -> user.getId() == id);
    }
}

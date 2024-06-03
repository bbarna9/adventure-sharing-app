package com.portfolio.adventure_backend.controllers;

import com.portfolio.adventure_backend.models.User;
import com.portfolio.adventure_backend.repositories.UserRepository;
import com.portfolio.adventure_backend.services.UserService;
import com.portfolio.adventure_backend.config.JwtConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // ----------------------------- GET ALL USERS ------------------------------

    @GetMapping
    public List<User> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }

    // ----------------------------- CREATE USER ------------------------------

    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    // -------------------------- GET USER FROM JWT ---------------------------

    @GetMapping("/profile")
    public User findUserByJwt(@RequestHeader(JwtConstant.JWT_HEADER) String jwt) throws Exception {
        return userService.findUserByJwt(jwt);
    }


    // ----------------------------- DELETE USER ------------------------------

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {
        return userService.deleteUser(userId);
    }
}

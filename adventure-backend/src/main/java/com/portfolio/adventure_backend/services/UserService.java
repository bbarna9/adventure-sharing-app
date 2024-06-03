package com.portfolio.adventure_backend.services;

import com.portfolio.adventure_backend.repositories.UserRepository;
import com.portfolio.adventure_backend.config.JwtProvider;
import com.portfolio.adventure_backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    // -------------------------------- GET ALL USERS ------------------------------

    public List<User> getAllUsers() throws Exception {
        List<User> findUsers = userRepository.findAll();
        if (findUsers.isEmpty()) {
            throw new Exception("No users added to the database yet.");
        } else {
            return findUsers;
        }
    }

    // ----------------------------- CREATE A USER ------------------------------

    public User createUser(@RequestBody User user) throws Exception {
        User findExistingUser = userRepository.findByEmail(user.getEmail());
        if (findExistingUser != null) {
            throw new Exception("Email already in use with an account!");
        } else {
            User savedUser = userRepository.save(user);
            return savedUser;
        }
    }

    // ---------------------------- FIND USER BY JWT -----------------------------

    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if (email == null) {
            throw new Exception("Provide a valid email!");
        }

        User findExistingUser = userRepository.findByEmail(email);
        if (findExistingUser == null) {
            throw new Exception("User not found with the email: " + email);
        }

        return findExistingUser;
    }


    // ----------------------------- DELETE A USER ------------------------------

    public String deleteUser(@PathVariable Long userId) throws Exception {
        Optional<User> findExistingUser = userRepository.findById(userId);
        if (findExistingUser.isEmpty()) {
            throw new Exception("User not found with id " + userId);
        } else {
            userRepository.deleteById(userId);
            return "User successfully deleted with the id " + userId;
        }
    }
}

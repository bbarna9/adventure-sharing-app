package com.portfolio.adventure_backend.controllers;

import com.portfolio.adventure_backend.models.User;
import com.portfolio.adventure_backend.repositories.UserRepository;
import com.portfolio.adventure_backend.response.AuthResponse;
import com.portfolio.adventure_backend.config.JwtProvider;
import com.portfolio.adventure_backend.requests.LoginRequest;
import com.portfolio.adventure_backend.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser (@RequestBody User user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullname = user.getFullName();

        User exists = userRepository.findByEmail(email);
        if(exists != null) {
            throw new Exception("Email is already in use with an account!");
        }
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullname);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Successful sign up!");
        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signInHandler(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Successful login!");
        return res;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if(userDetails == null) {
            throw new BadCredentialsException("User not found with the email: " + username);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}

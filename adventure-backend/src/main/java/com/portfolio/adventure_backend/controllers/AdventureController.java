package com.portfolio.adventure_backend.controllers;

import com.portfolio.adventure_backend.models.Adventure;
import com.portfolio.adventure_backend.models.User;
import com.portfolio.adventure_backend.repositories.UserRepository;
import com.portfolio.adventure_backend.services.AdventureService;
import com.portfolio.adventure_backend.services.UserService;
import com.portfolio.adventure_backend.config.JwtConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // ----------------------------- CREATE ADVENTURE ------------------------------

    @PostMapping()
    public Adventure createAdventure(@RequestBody Adventure adventure, @RequestHeader(JwtConstant.JWT_HEADER) String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return adventureService.createAdventure(adventure, user);
    }

    // ----------------------------- GET ALL ADVENTURES ------------------------------

    @GetMapping
    public List<Adventure> findAllAdventures() throws Exception {
        return adventureService.findAllAdventures();
    }

    // ----------------------------- GET AN ADVENTURE BY ID ------------------------------

    @GetMapping("/{adventureId}")
    public Adventure findAdventureById(@PathVariable Long adventureId) throws Exception {
        return adventureService.findAdventureById(adventureId);
    }

    // ----------------------------- UPDATE ADVENTURE ------------------------------

    @PutMapping("/{adventureId}")
    public Adventure updateAdventure(@RequestBody Adventure adventure, @PathVariable Long adventureId) throws Exception {
        return adventureService.updateAdventure(adventure, adventureId);
    }

    // ----------------------------- LIKE AN ADVENTURE -----------------------------

    @PutMapping("/{adventureId}/like")
    public Adventure likeAdventure(@PathVariable Long adventureId, @RequestHeader(JwtConstant.JWT_HEADER) String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return adventureService.likeAdventure(adventureId, user.getId());
    }

    // ----------------------------- DELETE ADVENTURE ------------------------------

    @DeleteMapping("/{adventureId}")
    public void deleteAdventureById(@PathVariable Long adventureId) throws Exception {
        adventureService.deleteAdventure(adventureId);
    }
}

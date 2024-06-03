package com.portfolio.adventure_backend.services;

import com.portfolio.adventure_backend.models.Adventure;
import com.portfolio.adventure_backend.models.User;
import com.portfolio.adventure_backend.repositories.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    // ----------------------------- CREATE ADVENTURE ------------------------------

    public Adventure createAdventure(Adventure adventure, User user) {
        Adventure newAdventure = new Adventure();

        newAdventure.setTitle(adventure.getTitle());
        newAdventure.setLocation(adventure.getLocation());
        newAdventure.setDescription(adventure.getDescription());
        newAdventure.setImageUrl(adventure.getImageUrl());
        newAdventure.setCost(adventure.getCost());
        newAdventure.setMonthOfTravel(adventure.getMonthOfTravel());
        newAdventure.setLengthOfTravel(adventure.getLengthOfTravel());
        newAdventure.setUser(user);
        newAdventure.setCreatedAt(LocalDateTime.now());

        return adventureRepository.save(newAdventure);
    }

    // ----------------------------- GET ALL ADVENTURES ------------------------------

    public List<Adventure> findAllAdventures() throws Exception {
        List<Adventure> adventure = adventureRepository.findAll();
        if(adventure.isEmpty()) {
            throw new Exception("No adventures added to the database yet.");
        }
        return adventure;
    }

    // ----------------------------- FIND AN ADVENTURE BY ID ------------------------------

    public Adventure findAdventureById(Long adventureId) throws Exception {
        Optional<Adventure> adventure = adventureRepository.findById(adventureId);
        if(adventure.isPresent()) {
            return adventure.get();
        } else {
            throw new Exception("Adventure not found with the id " + adventureId);
        }
    }

    // ----------------------------- UPDATE ADVENTURE ------------------------------

    public Adventure updateAdventure(Adventure adventure, Long adventureId) throws Exception {
        Adventure existingAdventure = findAdventureById(adventureId);

        if(existingAdventure == null) {
            throw new Exception("Adventure not found with the id " + adventureId);
        }

        if (adventure.getTitle() != null) {
            existingAdventure.setTitle(adventure.getTitle());
        }
        if(adventure.getLocation() != null) {
            existingAdventure.setLocation(adventure.getLocation());
        }
        if(adventure.getDescription() != null) {
            existingAdventure.setDescription(adventure.getDescription());
        }
        if(adventure.getImageUrl() != null) {
            existingAdventure.setImageUrl(adventure.getImageUrl());
        }
        if(adventure.getCost() != null) {
            existingAdventure.setCost(adventure.getCost());
        }
        if(adventure.getMonthOfTravel() != null) {
            existingAdventure.setMonthOfTravel(adventure.getMonthOfTravel());
        }
        if(adventure.getLengthOfTravel() != null) {
            existingAdventure.setLengthOfTravel(adventure.getLengthOfTravel());
        }
        if(adventure.getLikes() != null) {
            existingAdventure.setLikes(adventure.getLikes());
        }

        return adventureRepository.save(existingAdventure);
    }

    // ----------------------------- LIKE ADVENTURE ------------------------------

    public Adventure likeAdventure(Long adventureId, Long userId) throws Exception {
        Adventure adventure = findAdventureById(adventureId);

        if(adventure == null) {
            throw new Exception("Adventure not found with the id " + adventureId);
        }

        if(adventure.getLikes().contains(userId)){
            adventure.getLikes().remove(userId);
        } else {
            adventure.getLikes().add(userId);
        }
        return adventureRepository.save(adventure);
    }

    // ----------------------------- DELETE ADVENTURE ------------------------------

    public void deleteAdventure(Long adventureId) throws Exception {
        Adventure adventure = findAdventureById(adventureId);
        if(adventure == null) {
            throw new Exception("Adventure not found with the id " + adventureId);
        }
        adventureRepository.deleteById(adventureId);
    }
}

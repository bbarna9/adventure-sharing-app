package com.portfolio.adventure_backend.repositories;

import com.portfolio.adventure_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}

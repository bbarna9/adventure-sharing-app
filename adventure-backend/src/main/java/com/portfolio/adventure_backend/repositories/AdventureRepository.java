package com.portfolio.adventure_backend.repositories;

import com.portfolio.adventure_backend.models.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
}

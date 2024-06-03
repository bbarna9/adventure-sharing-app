package com.portfolio.adventure_backend.models;

import com.portfolio.adventure_backend.enums.PriceRange;
import com.portfolio.adventure_backend.enums.TravelLength;
import com.portfolio.adventure_backend.enums.TravelMonth;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Adventure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String location;
    private String description;
    private String imageUrl;
    private PriceRange cost;
    private TravelMonth monthOfTravel;
    private TravelLength lengthOfTravel;
    private List<Long> likes = new ArrayList<>();
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;
}

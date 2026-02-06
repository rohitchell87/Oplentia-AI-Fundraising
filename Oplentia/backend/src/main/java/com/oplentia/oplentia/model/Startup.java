package com.oplentia.oplentia.model;

import jakarta.persistence.*;

/**
 * The Startup Entity represents the core data model for Oplentia.
 * It maps directly to the 'startups' table in your database.
 */
@Entity
@Table(name = "startups")
public class Startup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the database

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String domain;

    private int revenue;
    private int traction;
    private int engagement;
    private int risk;
    
    // We store the AI-calculated score here for persistence
    private int score;
    private int aiScore;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "owner_id")
    private Long ownerId;

    // Default constructor required by JPA
    public Startup() {}

    // Convenience constructor for creating new startups
    public Startup(String name, String domain, int revenue, int traction, int engagement, int risk) {
        this.name = name;
        this.domain = domain;
        this.revenue = revenue;
        this.traction = traction;
        this.engagement = engagement;
        this.risk = risk;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public int getRevenue() { return revenue; }
    public void setRevenue(int revenue) { this.revenue = revenue; }

    public int getTraction() { return traction; }
    public void setTraction(int traction) { this.traction = traction; }

    public int getRisk() { return risk; }
    public void setRisk(int risk) { this.risk = risk; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getAiScore() { return aiScore; }
    public void setAiScore(int aiScore) { this.aiScore = aiScore; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}
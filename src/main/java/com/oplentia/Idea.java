package com.oplentia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String founderName;
    private String email;
    private double aiScore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Idea() {}

    public Idea(String title, String description, String founderName, String email, User user) {
        this.title = title;
        this.description = description;
        this.founderName = founderName;
        this.email = email;
        this.user = user;
        this.aiScore = Math.random() * 100; // Simulate AI score
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFounderName() { return founderName; }
    public void setFounderName(String founderName) { this.founderName = founderName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getAiScore() { return aiScore; }
    public void setAiScore(double aiScore) { this.aiScore = aiScore; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
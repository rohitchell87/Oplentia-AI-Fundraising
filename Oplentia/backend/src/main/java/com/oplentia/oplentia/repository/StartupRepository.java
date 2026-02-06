package com.oplentia.oplentia.repository;

import com.oplentia.oplentia.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Long> {
    
    // Custom query to get the leaderboard (High to Low score)
    List<Startup> findAllByOrderByScoreDesc();
    
    // Find startups by owner
    List<Startup> findByOwnerId(Long ownerId);
}
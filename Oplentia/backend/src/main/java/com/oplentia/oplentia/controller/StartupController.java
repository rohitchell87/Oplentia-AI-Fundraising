package com.oplentia.oplentia.controller;

import com.oplentia.oplentia.model.Startup;
import com.oplentia.oplentia.model.User;
import com.oplentia.oplentia.repository.StartupRepository;
import com.oplentia.oplentia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/startups")
@CrossOrigin(origins = "*")
public class StartupController {
    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStartup(@PathVariable Long id) {
        try {
            Startup startup = startupRepository.findById(id).orElse(null);
            if (startup == null) {
                return ResponseEntity.status(404).body(Map.of("error", "Startup not found"));
            }
            return ResponseEntity.ok(startup);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStartup(@PathVariable Long id, @RequestHeader("Authorization") String token, @RequestBody Startup updates) {
        try {
            User user = userService.findBySessionToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
            }
            Startup startup = startupRepository.findById(id).orElse(null);
            if (startup == null) {
                return ResponseEntity.status(404).body(Map.of("error", "Startup not found"));
            }
            if (!startup.getOwnerId().equals(user.getId())) {
                return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
            }
            if (updates.getName() != null) startup.setName(updates.getName());
            if (updates.getDomain() != null) startup.setDomain(updates.getDomain());
            if (updates.getRevenue() > 0) startup.setRevenue(updates.getRevenue());
            if (updates.getDescription() != null) startup.setDescription(updates.getDescription());
            Startup saved = startupRepository.save(startup);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStartup(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            User user = userService.findBySessionToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
            }
            Startup startup = startupRepository.findById(id).orElse(null);
            if (startup == null) {
                return ResponseEntity.status(404).body(Map.of("error", "Startup not found"));
            }
            if (!startup.getOwnerId().equals(user.getId())) {
                return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
            }
            startupRepository.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Startup deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}

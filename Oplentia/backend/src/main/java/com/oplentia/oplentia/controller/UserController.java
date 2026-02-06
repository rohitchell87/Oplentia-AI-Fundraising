package com.oplentia.oplentia.controller;

import com.oplentia.oplentia.model.Startup;
import com.oplentia.oplentia.model.User;
import com.oplentia.oplentia.repository.StartupRepository;
import com.oplentia.oplentia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StartupRepository startupRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        try {
            User user = userService.findBySessionToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
            }
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            response.put("fullName", user.getFullName());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        try {
            User user = userService.findBySessionToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
            }
            if (request.containsKey("fullName")) {
                user.setFullName(request.get("fullName"));
            }
            User updated = userService.save(user);
            Map<String, Object> response = new HashMap<>();
            response.put("id", updated.getId());
            response.put("email", updated.getEmail());
            response.put("fullName", updated.getFullName());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/me/password")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        try {
            User user = userService.findBySessionToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
            }
            userService.changePassword(user.getId(), request.get("oldPassword"), request.get("newPassword"));
            return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}/startups")
    public ResponseEntity<?> getUserStartups(@PathVariable Long id) {
        try {
            List<Startup> startups = startupRepository.findByOwnerId(id);
            return ResponseEntity.ok(startups);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}

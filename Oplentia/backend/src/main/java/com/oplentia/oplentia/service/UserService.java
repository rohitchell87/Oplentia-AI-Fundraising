package com.oplentia.oplentia.service;

import com.oplentia.oplentia.model.User;
import com.oplentia.oplentia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(String email, String fullName, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User(email, fullName, passwordEncoder.encode(password));
        user.setSessionToken(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }
        user.setSessionToken(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public User findBySessionToken(String token) {
        return userRepository.findBySessionToken(token);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new RuntimeException("Current password is incorrect");
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}

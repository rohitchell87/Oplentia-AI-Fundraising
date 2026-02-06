package com.oplentia.oplentia.repository;

import com.oplentia.oplentia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findBySessionToken(String sessionToken);
}

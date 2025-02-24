package com.edu.shop.repository;

import com.edu.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String email);
}

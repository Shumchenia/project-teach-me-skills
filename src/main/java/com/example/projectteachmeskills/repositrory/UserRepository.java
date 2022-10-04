package com.example.projectteachmeskills.repositrory;

import com.example.projectteachmeskills.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserName(String username);
}
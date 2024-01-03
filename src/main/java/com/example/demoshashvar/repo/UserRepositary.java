package com.example.demoshashvar.repo;

import com.example.demoshashvar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositary extends JpaRepository<User, UUID> {
    boolean existsByFirstNameAndSurName(String firstName, String surName);
}

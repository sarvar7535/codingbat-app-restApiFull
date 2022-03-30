package com.example.codingbatapp.repository;

import com.example.codingbatapp.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    boolean existsByEmail(String email);
}

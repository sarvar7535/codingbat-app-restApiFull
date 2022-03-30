package com.example.codingbatapp.repository;

import com.example.codingbatapp.entity.ProblemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemsRepository extends JpaRepository<ProblemsEntity, Long> {
}

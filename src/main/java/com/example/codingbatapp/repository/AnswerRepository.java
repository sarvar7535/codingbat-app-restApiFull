package com.example.codingbatapp.repository;

import com.example.codingbatapp.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}

package com.example.codingbatapp.repository;

import com.example.codingbatapp.entity.LanguagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguagesRepository extends JpaRepository<LanguagesEntity, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}

package com.example.codingbatapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "problems")
public class ProblemsEntity extends  BaseModel{

    private String solution;

    private String code;

    private String example;

    @ManyToOne
    private ThemeEntity theme;

}

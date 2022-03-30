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
@Table(name = "exampleSolution")
public class ExampleSolutionEntity extends BaseModel{

    private String description;

    private String code;

    @ManyToOne
    CategoryEntity category;

}

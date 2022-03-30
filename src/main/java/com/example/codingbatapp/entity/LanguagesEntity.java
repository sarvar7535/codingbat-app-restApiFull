package com.example.codingbatapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@Entity
@Table(name = "languages")
public class LanguagesEntity extends BaseModel {
}

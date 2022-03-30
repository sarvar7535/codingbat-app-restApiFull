package com.example.codingbatapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    @NotNull(message = "title is mandatory")
    private String title;

    @NotNull(message = "title is mandatory")
    private Long id;

}

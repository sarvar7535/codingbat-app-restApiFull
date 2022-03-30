package com.example.codingbatapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ThemeDto {

    @NotNull(message = "the theme cannot be bush")
    private String name;

    @NotNull(message = "the description cannot be bush")
    private String description;

    @NotNull(message = "the languagesId cannot be bush")
    private Long languagesId;

}

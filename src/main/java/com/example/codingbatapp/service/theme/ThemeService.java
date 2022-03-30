package com.example.codingbatapp.service.theme;

import com.example.codingbatapp.entity.ThemeEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.payload.ThemeDto;

import java.util.List;

public interface ThemeService {

    List<ThemeEntity> getThemes(int page, int size);

    ThemeEntity getTheme(Long id);

    ApiResponse saveTheme(ThemeDto themeDto);

    ApiResponse editTheme(Long id , ThemeDto themeDto);

    ApiResponse deleteTheme(Long id);
}

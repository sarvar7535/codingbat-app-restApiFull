package com.example.codingbatapp.controller;

import com.example.codingbatapp.entity.ThemeEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.payload.ThemeDto;
import com.example.codingbatapp.service.theme.ThemeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theme")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ThemeController  {

    @Autowired
    private ThemeServiceImpl themeService;

    @PreAuthorize(value = "hasAnyRole('DIRECTOR', 'MANAGER')")
    @GetMapping("/list")
    public ResponseEntity<List<ThemeEntity>> getThemes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<ThemeEntity> themes = themeService.getThemes(page, size);
        return ResponseEntity.status(200).body(themes);
    }


    @PreAuthorize(value = "hasAnyRole('DIRECTOR', 'MANAGER', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ThemeEntity> getTheme(@PathVariable  Long id) {
        ThemeEntity theme = themeService.getTheme(id);
        if (theme == null)
            return ResponseEntity.status(409).body(null);

        return ResponseEntity.status(200).body(theme);
    }


    @PreAuthorize(value = "hasAnyRole('DIRECTOR')")
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveTheme(@RequestBody ThemeDto themeDto) {
        ApiResponse apiResponse = themeService.saveTheme(themeDto);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyRole('DIRECTOR')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editTheme(@PathVariable Long id, @RequestBody ThemeDto themeDto) {

        ApiResponse apiResponse = themeService.editTheme(id, themeDto);

        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);
        return ResponseEntity.status(202).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyRole('DIRECTOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTheme(@PathVariable Long id) {
        ApiResponse apiResponse = themeService.deleteTheme(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(202).body(apiResponse);
        else
            return ResponseEntity.status(409).body(apiResponse);
    }
}

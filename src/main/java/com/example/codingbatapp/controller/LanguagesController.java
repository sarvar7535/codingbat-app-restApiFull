package com.example.codingbatapp.controller;

import com.example.codingbatapp.entity.LanguagesEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.service.languages.LanguagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController  {

    @Autowired
    private LanguagesServiceImpl languagesService;



    @GetMapping("/list")
    public ResponseEntity<List<LanguagesEntity>> getLanguages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        List<LanguagesEntity> languages = languagesService.getLanguages(page, size);

        return ResponseEntity.status(200).body(languages);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LanguagesEntity> getLanguage(@PathVariable Long id) {
        LanguagesEntity language = languagesService.getLanguage(id);
        if (language == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(202).body(language);
    }

    @PostMapping("/save")

    public ResponseEntity<ApiResponse> saveLanguages(@RequestBody  LanguagesEntity languagesEntity) {
        ApiResponse apiResponse = languagesService.saveLanguages(languagesEntity);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/edit")
    public ResponseEntity<ApiResponse> editLanguages(@PathVariable Long id, @RequestBody LanguagesEntity languagesEntity) {
        ApiResponse apiResponse = languagesService.editLanguages(id, languagesEntity);
        if (!apiResponse.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        }else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteLanguages(@PathVariable Long id) {
        ApiResponse apiResponse = languagesService.deleteLanguages(id);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
}

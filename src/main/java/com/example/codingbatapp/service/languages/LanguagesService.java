package com.example.codingbatapp.service.languages;


import com.example.codingbatapp.entity.LanguagesEntity;
import com.example.codingbatapp.payload.ApiResponse;

import java.util.List;

public interface LanguagesService {

    List<LanguagesEntity> getLanguages(int page, int size);

    LanguagesEntity getLanguage(Long id);

    ApiResponse saveLanguages(LanguagesEntity languagesEntity);

    ApiResponse editLanguages(Long id, LanguagesEntity languagesEntity);

    ApiResponse deleteLanguages(Long id);

}

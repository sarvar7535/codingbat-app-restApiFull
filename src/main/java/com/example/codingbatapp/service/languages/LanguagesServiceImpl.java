package com.example.codingbatapp.service.languages;

import com.example.codingbatapp.entity.LanguagesEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.repository.LanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguagesServiceImpl implements LanguagesService {

    private final LanguagesRepository languagesRepository;

    @Autowired
    public LanguagesServiceImpl(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }

    @Override
    public List<LanguagesEntity> getLanguages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LanguagesEntity> languagesPage = languagesRepository.findAll(pageable);
        List<LanguagesEntity> content = languagesPage.getContent();
        return content;
    }

    @Override
    public LanguagesEntity getLanguage(Long id) {
        Optional<LanguagesEntity> optionalById = languagesRepository.findById(id);
        if (optionalById.isEmpty()) {
            return null;
        }
        return optionalById.get();
    }

    @Override
    public ApiResponse saveLanguages(LanguagesEntity languagesEntity) {
        if (languagesRepository.existsByName(languagesEntity.getName()))
            return new ApiResponse("alerady languages name", false);

        LanguagesEntity save = languagesRepository.save(languagesEntity);

        return new ApiResponse(languagesEntity.getName() + " language save", true);
    }

    @Override
    public ApiResponse editLanguages(Long id, LanguagesEntity languagesEntity) {
        boolean byNameAndIdNot = languagesRepository.existsByNameAndIdNot(languagesEntity.getName(), id);
        if (byNameAndIdNot) {
            new ApiResponse(languagesEntity.getName() + " such a language exists by name", false);
        }
        Optional<LanguagesEntity> byId = languagesRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse("such a language does not exist", false);
        }

        LanguagesEntity languagesEntityUz = byId.get();
        languagesEntityUz.setName(languagesEntity.getName());
        languagesRepository.save(languagesEntityUz);
        return new ApiResponse("edit languages", true);
    }

    @Override
    public ApiResponse deleteLanguages(Long id) {
        try {
            languagesRepository.deleteById(id);
            return new ApiResponse("delete language", true);
        } catch (Exception e) {
            return new ApiResponse("not found languages", false);
        }
    }
}

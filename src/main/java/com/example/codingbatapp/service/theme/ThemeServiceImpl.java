package com.example.codingbatapp.service.theme;

import com.example.codingbatapp.entity.LanguagesEntity;
import com.example.codingbatapp.entity.ThemeEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.payload.ThemeDto;
import com.example.codingbatapp.repository.LanguagesRepository;
import com.example.codingbatapp.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    private final LanguagesRepository languagesRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository, LanguagesRepository languagesRepository) {
        this.themeRepository = themeRepository;
        this.languagesRepository = languagesRepository;
    }

    @Override
    public List<ThemeEntity> getThemes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ThemeEntity> entityPage = themeRepository.findAll(pageable);
        return entityPage.getContent();
    }

    @Override
    public ThemeEntity getTheme(Long id) {
        Optional<ThemeEntity> byId = themeRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public ApiResponse saveTheme(ThemeDto themeDto) {
        boolean existsByName = themeRepository.existsByName(themeDto.getName());
        if (existsByName)
            return new ApiResponse("there is such a theme", false);
        Optional<LanguagesEntity> languages
                = languagesRepository.findById(themeDto.getLanguagesId());
        if (!languages.isPresent())
            return new ApiResponse("not found languages", false);

        ThemeEntity themeEntity = new ThemeEntity();
        themeEntity.setName(themeDto.getName());
        themeEntity.setDescription(themeDto.getDescription());
        themeEntity.setLanguages(languages.get());
        themeRepository.save(themeEntity);

        return new ApiResponse("saved theme", true);
    }

    @Override
    public ApiResponse editTheme(Long id, ThemeDto themeDto) {
        boolean existsByNameAndIdNot
                = themeRepository.existsByNameAndIdNot(themeDto.getName(), id);

        if (existsByNameAndIdNot)
            return new ApiResponse("bunaqa mavzu bor", false);

        Optional<LanguagesEntity> languages
                = languagesRepository.findById(themeDto.getLanguagesId());
        if (!languages.isPresent())
            return new ApiResponse("not found languages", false);

        Optional<ThemeEntity> byId = themeRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("bunday " + id + " li theme yuq", false);

        ThemeEntity themeEntity = byId.get();
        themeEntity.setName(themeDto.getName());
        themeEntity.setDescription(themeDto.getDescription());
        themeEntity.setLanguages(languages.get());
        themeRepository.save(themeEntity);

        return new ApiResponse("edit theme", true);
    }

    @Override
    public ApiResponse deleteTheme(Long id) {
        try{
            themeRepository.deleteById(id);
            return new ApiResponse("delete theme",true);
        }catch (Exception e){
            return new ApiResponse("Xatolik", false);
        }

    }
}

package com.example.codingbatapp.service.category;

import com.example.codingbatapp.entity.CategoryEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getLanguages(int page, int size);

    CategoryEntity getCategory(Long id);

    ApiResponse saveCategory(CategoryDto categoryDto);

    ApiResponse editCategory(Long id, CategoryDto categoryDto);

    ApiResponse deleteCategory(Long id);

}

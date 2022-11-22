package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.Category;
import com.book.bookshareserver.data.repository.CategoryRepository;
import com.book.bookshareserver.representation.dto.CategoryDto;
import com.book.bookshareserver.representation.dto.converter.CategoryDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryDtoConverter categoryDtoConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
    }


    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryDtoConverter::toCategoryDto)
                .collect(Collectors.toList());
    }
}

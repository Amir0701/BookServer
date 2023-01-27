package com.book.bookshareserver.representation.dto.converter.impl;

import com.book.bookshareserver.data.model.Category;
import com.book.bookshareserver.representation.dto.CategoryDto;
import com.book.bookshareserver.representation.dto.converter.CategoryDtoConverter;
import com.book.bookshareserver.representation.dto.converter.PublicationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverterImpl implements CategoryDtoConverter {

    private final PublicationDtoConverter publicationDtoConverter;

    @Autowired
    public CategoryDtoConverterImpl(PublicationDtoConverter publicationDtoConverter){
        this.publicationDtoConverter = publicationDtoConverter;
    }

    @Override
    public Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        if(categoryDto.getPublicationDtoList() != null){
            category.setPublicationList(categoryDto.getPublicationDtoList()
                    .stream()
                    .map(publicationDtoConverter::toPublication)
                    .collect(Collectors.toList()));
        }
        else {
            category.setPublicationList(new ArrayList<>());
        }
        return category;
    }

    @Override
    public CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setPublicationDtoList(
                category.getPublicationList()
                        .stream()
                        .map(publicationDtoConverter::toPublicationDto)
                        .collect(Collectors.toList()));

        return categoryDto;
    }
}

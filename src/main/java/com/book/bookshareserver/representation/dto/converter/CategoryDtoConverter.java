package com.book.bookshareserver.representation.dto.converter;

import com.book.bookshareserver.data.model.Category;
import com.book.bookshareserver.representation.dto.CategoryDto;
import org.springframework.stereotype.Component;

public interface CategoryDtoConverter {
    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
}

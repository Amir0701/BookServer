package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.Category;
import com.book.bookshareserver.representation.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategory();
}

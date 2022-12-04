package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.representation.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> getAll();
}

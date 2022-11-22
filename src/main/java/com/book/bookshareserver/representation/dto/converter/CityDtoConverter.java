package com.book.bookshareserver.representation.dto.converter;

import com.book.bookshareserver.data.model.City;
import com.book.bookshareserver.representation.dto.CityDto;

import java.util.List;

public interface CityDtoConverter {
    City toCity(CityDto cityDto);
    CityDto toCityDto(City city);
}

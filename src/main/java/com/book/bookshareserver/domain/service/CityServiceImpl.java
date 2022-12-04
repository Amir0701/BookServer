package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.City;
import com.book.bookshareserver.data.repository.CityRepository;
import com.book.bookshareserver.representation.dto.CityDto;
import com.book.bookshareserver.representation.dto.converter.CityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityServiceImpl implements CityService{
    private final CityRepository cityRepository;
    private final CityDtoConverter cityDtoConverter;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository,
                           CityDtoConverter cityDtoConverter){
        this.cityRepository = cityRepository;
        this.cityDtoConverter = cityDtoConverter;
    }

    @Override
    public List<CityDto> getAll() {
        List<CityDto> cityDtoList = cityRepository.findAll()
                .stream()
                .map(cityDtoConverter::toCityDto)
                .collect(Collectors.toList());
        return cityDtoList;
    }
}

package com.book.bookshareserver.representation.dto.converter.impl;

import com.book.bookshareserver.data.model.City;
import com.book.bookshareserver.representation.dto.CityDto;
import com.book.bookshareserver.representation.dto.converter.CityDtoConverter;
import com.book.bookshareserver.representation.dto.converter.PublicationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CityDtoConverterImpl implements CityDtoConverter {
    private final PublicationDtoConverter publicationDtoConverter;

    @Autowired
    public CityDtoConverterImpl(PublicationDtoConverter publicationDtoConverter){
        this.publicationDtoConverter = publicationDtoConverter;
    }

    @Override
    public City toCity(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        if(cityDto.getPublicationDtoList() != null){
            city.setPublicationList(
                    cityDto.getPublicationDtoList()
                            .stream()
                            .map(publicationDtoConverter::toPublication)
                            .collect(Collectors.toList())
            );
        }
        else {
            city.setPublicationList(new ArrayList<>());
        }
        return city;
    }

    @Override
    public CityDto toCityDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(cityDto.getId());
        cityDto.setName(city.getName());
        cityDto.setPublicationDtoList(
                city.getPublicationList()
                        .stream()
                        .map(publicationDtoConverter::toPublicationDto)
                        .collect(Collectors.toList())
        );
        return cityDto;
    }
}

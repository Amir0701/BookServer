package com.book.bookshareserver.representation.dto.converter.impl;

import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.representation.dto.PublicationDto;
import com.book.bookshareserver.representation.dto.converter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PublicationDtoConverterImpl implements PublicationDtoConverter {
    private final ImageDtoConverter imageDtoConverter;
    private final CategoryDtoConverter categoryDtoConverter;
    private final CityDtoConverter cityDtoConverter;

    @Autowired
    public PublicationDtoConverterImpl(ImageDtoConverter imageDtoConverter,
                                       CategoryDtoConverter categoryDtoConverter,
                                       CityDtoConverter cityDtoConverter){
        this.imageDtoConverter = imageDtoConverter;
        this.categoryDtoConverter = categoryDtoConverter;
        this.cityDtoConverter = cityDtoConverter;
    }

    @Override
    public Publication toPublication(PublicationDto publicationDto) {
        Publication publication = new Publication();
        publication.setId(publicationDto.getId());
        publication.setName(publicationDto.getName());
        publication.setPublishedAt(publicationDto.getPublishedAt());
        publication.setDescription(publicationDto.getDescription());
        publication.setCategory(categoryDtoConverter.toCategory(publicationDto.getCategory()));
        publication.setCity(cityDtoConverter.toCity(publicationDto.getCityDto()));
        publication.setUser(new User(publicationDto.getUserId()));
        publication.setImages(publicationDto.getImagesDto()
                .stream()
                .map(imageDtoConverter::toImage)
                .collect(Collectors.toList()));
        return publication;
    }

    @Override
    public PublicationDto toPublicationDto(Publication publication) {
        PublicationDto publicationDto = new PublicationDto();
        publicationDto.setId(publication.getId());
        publicationDto.setName(publication.getName());
        publicationDto.setDescription(publication.getDescription());
        publicationDto.setPublishedAt(publication.getPublishedAt());
        publicationDto.setCityDto(cityDtoConverter.toCityDto(publication.getCity()));
        publicationDto.setCategory(categoryDtoConverter.toCategoryDto(publication.getCategory()));
        publicationDto.setUserId(publication.getUser().getId());
        publicationDto.setImagesDto(publication.getImages()
                .stream()
                .map(imageDtoConverter::toImageDto)
                .collect(Collectors.toList()));
        return publicationDto;
    }
}

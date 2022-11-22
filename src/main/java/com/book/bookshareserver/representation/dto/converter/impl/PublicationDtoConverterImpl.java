package com.book.bookshareserver.representation.dto.converter.impl;

import com.book.bookshareserver.data.model.Category;
import com.book.bookshareserver.data.model.City;
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

    @Autowired
    public PublicationDtoConverterImpl(ImageDtoConverter imageDtoConverter){
        this.imageDtoConverter = imageDtoConverter;
    }

    @Override
    public Publication toPublication(PublicationDto publicationDto) {
        Publication publication = new Publication();
        publication.setId(publicationDto.getId());
        publication.setName(publicationDto.getName());
        publication.setPublishedAt(publicationDto.getPublishedAt());
        publication.setDescription(publicationDto.getDescription());
        publication.setCategory(new Category(publicationDto.getCategoryId()));
        publication.setCity(new City(publicationDto.getCityId()));
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
        publicationDto.setId(publicationDto.getId());
        publicationDto.setName(publicationDto.getName());
        publicationDto.setDescription(publicationDto.getDescription());
        publicationDto.setPublishedAt(publication.getPublishedAt());
        publicationDto.setCityId(publication.getCity().getId());
        publicationDto.setCategoryId(publication.getCategory().getId());
        publicationDto.setUserId(publication.getUser().getId());
        publicationDto.setImagesDto(publication.getImages()
                .stream()
                .map(imageDtoConverter::toImageDto)
                .collect(Collectors.toList()));
        return publicationDto;
    }
}

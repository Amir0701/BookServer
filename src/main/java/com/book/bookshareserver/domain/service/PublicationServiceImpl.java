package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.data.repository.PublicationRepository;
import com.book.bookshareserver.representation.dto.PublicationDto;
import com.book.bookshareserver.representation.dto.converter.PublicationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublicationServiceImpl implements PublicationService{
    private final PublicationRepository publicationRepository;
    private final PublicationDtoConverter publicationDtoConverter;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository,
                                  PublicationDtoConverter publicationDtoConverter){
        this.publicationRepository = publicationRepository;
        this.publicationDtoConverter = publicationDtoConverter;
    }

    @Override
    public List<PublicationDto> getPublicationsByCategory(Long categoryId) {
        List<Publication> publications = publicationRepository.getPublicationsByCategoryId(categoryId);
        List<PublicationDto> publicationDtoList = publications.stream()
                .map(publicationDtoConverter::toPublicationDto)
                .collect(Collectors.toList());

        return publicationDtoList;
    }

    @Override
    public List<PublicationDto> getPublicationsByUser(Long userId) {
        List<Publication> publications = publicationRepository.getPublicationsByUserId(userId);

        return publications.stream()
                .map(publicationDtoConverter::toPublicationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicationDto> getPublicationsByCity(Long cityId) {
        List<Publication> publications = publicationRepository.getPublicationsByCityId(cityId);

        return publications.stream()
                .map(publicationDtoConverter::toPublicationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicationDto> getPublicationsByCityAndCategory(Long cityId, Long categoryId) {
        List<Publication> publications = publicationRepository.getPublicationsByCityAndCategory(cityId, categoryId);

        return publications.stream()
                .map(publicationDtoConverter::toPublicationDto)
                .collect(Collectors.toList());
    }
}

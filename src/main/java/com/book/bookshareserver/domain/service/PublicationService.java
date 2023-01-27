package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.representation.dto.PublicationDto;

import java.util.List;

public interface PublicationService {
    List<PublicationDto> getPublicationsByCategory(Long categoryId);
    List<PublicationDto> getPublicationsByUser(Long userId);
    List<PublicationDto> getPublicationsByCity(Long cityId);
    List<PublicationDto> getPublicationsByCityAndCategory(Long cityId, Long categoryId);
}

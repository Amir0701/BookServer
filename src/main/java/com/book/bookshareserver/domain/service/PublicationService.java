package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.representation.dto.PublicationDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicationService {
    List<PublicationDto> getPublicationsByCategory(Long categoryId);
    List<PublicationDto> getPublicationsByUser(Long userId);
    List<PublicationDto> getPublicationsByCity(Long cityId);
    List<PublicationDto> getPublicationsByCityAndCategory(Long cityId, Long categoryId);
    PublicationDto addPublication(PublicationDto publicationDto);
    List<PublicationDto> getFavoritePublicationsByUserId(Long userId);
    List<PublicationDto> getAllPublications();
}

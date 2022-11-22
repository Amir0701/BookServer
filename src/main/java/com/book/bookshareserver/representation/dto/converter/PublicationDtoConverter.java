package com.book.bookshareserver.representation.dto.converter;

import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.representation.dto.PublicationDto;

import java.util.List;

public interface PublicationDtoConverter {
    Publication toPublication(PublicationDto publicationDto);
    PublicationDto toPublicationDto(Publication publication);
}

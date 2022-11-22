package com.book.bookshareserver.representation.dto.converter;

import com.book.bookshareserver.data.model.Image;
import com.book.bookshareserver.representation.dto.ImageDto;

import java.util.List;

public interface ImageDtoConverter {
    Image toImage(ImageDto imageDto);
    ImageDto toImageDto(Image image);
}

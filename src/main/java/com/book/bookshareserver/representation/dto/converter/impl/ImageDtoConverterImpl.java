package com.book.bookshareserver.representation.dto.converter.impl;

import com.book.bookshareserver.data.model.Image;
import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.representation.dto.ImageDto;
import com.book.bookshareserver.representation.dto.converter.ImageDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class ImageDtoConverterImpl implements ImageDtoConverter {
    @Override
    public Image toImage(ImageDto imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setPath(imageDto.getPath());
        image.setPublication(new Publication(imageDto.getPublicationId()));
        return image;
    }

    @Override
    public ImageDto toImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setPath(image.getPath());
        imageDto.setPublicationId(image.getPublication().getId());
        return imageDto;
    }
}

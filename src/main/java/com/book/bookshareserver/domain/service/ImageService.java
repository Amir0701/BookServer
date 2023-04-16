package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.Image;
import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.representation.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<Image> addImage(MultipartFile[] multipartFiles, Publication publication);
    void addImage(MultipartFile[] multipartFiles, Long publicationId);
    void addImages(MultipartFile[] multipartFiles, Long publicationId);
    byte[] downloadImage(String fileName);
}

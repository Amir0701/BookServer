package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.Image;
import com.book.bookshareserver.data.model.Publication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<Image> addImage(MultipartFile[] multipartFiles, Publication publication);
    List<Image> addImage(MultipartFile[] multipartFiles, Long publicationId);
}

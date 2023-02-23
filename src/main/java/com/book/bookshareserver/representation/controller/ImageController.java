package com.book.bookshareserver.representation.controller;

import com.book.bookshareserver.domain.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping
    public void upload(@RequestParam("files") MultipartFile[] multipartFiles,
                       @RequestParam("publication_id") Long id){
        imageService.addImage(multipartFiles, id);
    }
}

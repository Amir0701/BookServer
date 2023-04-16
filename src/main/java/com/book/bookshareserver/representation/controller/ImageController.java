package com.book.bookshareserver.representation.controller;

import com.book.bookshareserver.domain.service.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/upload")
    public void upl(@RequestParam("files") MultipartFile[] multipartFiles,
                    @RequestParam("publication_id") Long id){
        imageService.addImages(multipartFiles, id);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = imageService.downloadImage(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}

package com.book.bookshareserver.representation.controller;

import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.domain.service.PublicationService;
import com.book.bookshareserver.representation.dto.PublicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService){
        this.publicationService = publicationService;
    }

    @GetMapping(params = "categoryId")
    public ResponseEntity<List<PublicationDto>> getPublicationsByCategoryId(@RequestParam Long categoryId){
        return ResponseEntity.ok(publicationService.getPublicationsByCategory(categoryId));
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<PublicationDto>> getPublicationsByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(publicationService.getPublicationsByUser(userId));
    }

    @GetMapping(params = "cityId")
    public ResponseEntity<List<PublicationDto>> getPublicationsByCityId(@RequestParam Long cityId){
        return ResponseEntity.ok(publicationService.getPublicationsByCity(cityId));
    }

    @GetMapping(params = {"cityId", "categoryId"})
    public ResponseEntity<List<PublicationDto>> getPublicationsByCityIdAndCategoryId(@RequestParam Long cityId,
                                                                                     @RequestParam Long categoryId){
        return ResponseEntity.ok(publicationService.getPublicationsByCityAndCategory(cityId, categoryId));
    }

    @PostMapping
    public ResponseEntity<PublicationDto> addPublication(@RequestBody PublicationDto publicationDto){
        return ResponseEntity.ok(publicationService.addPublication(publicationDto));
    }

    @GetMapping(value = "/favorite", params = "userId")
    public ResponseEntity<List<PublicationDto>> getFavoritePublicationByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(publicationService.getFavoritePublicationsByUserId(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PublicationDto>> getAllPublications(){
        return ResponseEntity.ok(publicationService.getAllPublications());
    }
}

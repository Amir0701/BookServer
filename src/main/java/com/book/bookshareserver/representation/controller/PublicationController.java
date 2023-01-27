package com.book.bookshareserver.representation.controller;

import com.book.bookshareserver.data.model.Publication;
import com.book.bookshareserver.domain.service.PublicationService;
import com.book.bookshareserver.representation.dto.PublicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.book.bookshareserver.representation.controller;

import com.book.bookshareserver.domain.service.CityService;
import com.book.bookshareserver.representation.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CityDto>> getAll(){
        return ResponseEntity.ok(cityService.getAll());
    }
}

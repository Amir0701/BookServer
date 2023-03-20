package com.book.bookshareserver.representation.dto;

import java.sql.Timestamp;
import java.util.List;

public class PublicationDto {
    private Long id;
    private Long userId;
    private CategoryDto category;
    private CityDto city;
    private String description;
    private String name;
    private String author;
    private Timestamp publishedAt;
    private List<ImageDto> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto categoryDto) {
        this.category = categoryDto;
    }

    public CityDto getCityDto() {
        return city;
    }

    public void setCityDto(CityDto cityDto) {
        this.city = cityDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<ImageDto> getImagesDto() {
        return images;
    }

    public void setImagesDto(List<ImageDto> images) {
        this.images = images;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

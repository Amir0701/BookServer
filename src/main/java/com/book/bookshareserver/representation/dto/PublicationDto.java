package com.book.bookshareserver.representation.dto;

import java.sql.Timestamp;
import java.util.List;

public class PublicationDto {
    private Long id;
    private Long userId;
    private Long categoryId;
    private Long cityId;
    private String description;
    private String name;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long id) {
        this.categoryId = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
}

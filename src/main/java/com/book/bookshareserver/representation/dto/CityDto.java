package com.book.bookshareserver.representation.dto;

import java.util.List;

public class CityDto {
    private Long id;
    private String name;
    private List<PublicationDto> publicationDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PublicationDto> getPublicationDtoList() {
        return publicationDtoList;
    }

    public void setPublicationDtoList(List<PublicationDto> publicationDtoList) {
        this.publicationDtoList = publicationDtoList;
    }
}

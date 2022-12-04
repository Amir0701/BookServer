package com.book.bookshareserver.representation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {
    private Long id;
    @Size(min = 1, max = 50)
    private String name;

    @Email  // todo msg
    @Pattern(regexp = "^.+@.+\\..+$")  // todo msg
    @Size(min = 5, max = 100)  // todo msg
    private String email;

    @Pattern(regexp = "^[a-zA-Z]+.*$")  // todo msg
    @Size(min = 6, max = 100)  // todo msg
    private String password;
    private String phoneNumber;
    private List<PublicationDto> publicationList;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<PublicationDto> getPublicationDtoList() {
        return publicationList;
    }

    public void setPublicationDtoList(List<PublicationDto> publicationList) {
        this.publicationList = publicationList;
    }
}

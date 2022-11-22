package com.book.bookshareserver.representation.dto.converter.impl;

import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.representation.dto.UserDto;
import com.book.bookshareserver.representation.dto.converter.PublicationDtoConverter;
import com.book.bookshareserver.representation.dto.converter.UserDtoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserDtoConverterImpl implements UserDtoConverter {
    private final PublicationDtoConverter publicationDtoConverter;

    public UserDtoConverterImpl(PublicationDtoConverter publicationDtoConverter){
        this.publicationDtoConverter = publicationDtoConverter;
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        if(userDto.getPublicationDtoList() != null){
            user.setPublicationList(
                    userDto.getPublicationDtoList()
                            .stream()
                            .map(publicationDtoConverter::toPublication)
                            .collect(Collectors.toList())
            );
        }
        else {
            user.setPublicationList(new ArrayList<>());
        }
        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPublicationDtoList(
                user.getPublicationList()
                        .stream()
                        .map(publicationDtoConverter::toPublicationDto)
                        .collect(Collectors.toList())
        );
        return userDto;
    }
}

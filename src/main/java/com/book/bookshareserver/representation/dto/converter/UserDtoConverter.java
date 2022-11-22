package com.book.bookshareserver.representation.dto.converter;

import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.representation.dto.UserDto;

import java.util.List;

public interface UserDtoConverter {
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
}

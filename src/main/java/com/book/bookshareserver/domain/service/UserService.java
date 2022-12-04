package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.representation.dto.UserDto;

public interface UserService {
    User save(UserDto user);
}

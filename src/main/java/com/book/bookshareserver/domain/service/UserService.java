package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.representation.dto.UserDto;

public interface UserService {
    User save(UserDto user);
    UserDto getUserById(Long id);
    void addPublicationToFavorite(Long userId, Long publicationId);
    User getCurrentUser();
    UserDto getAuthUser();
}

package com.book.bookshareserver.representation.dto.converter;

import com.book.bookshareserver.data.model.RefreshToken;
import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.representation.dto.RefreshTokenDto;

public interface RefreshTokenDtoConverter {
    RefreshTokenDto toRefreshTokenDto(User user, String accessToken, String refreshToken);
}

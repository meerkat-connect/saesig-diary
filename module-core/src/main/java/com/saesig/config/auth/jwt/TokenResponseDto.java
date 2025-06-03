package com.saesig.config.auth.jwt;

import lombok.Builder;

@Builder
public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {
}

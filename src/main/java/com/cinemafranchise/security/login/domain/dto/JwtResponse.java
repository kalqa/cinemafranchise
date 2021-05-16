package com.cinemafranchise.security.login.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class JwtResponse {

    private final String username;
    private final String token;
}

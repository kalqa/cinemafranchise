package com.cinemafranchise.security.login;

import com.cinemafranchise.security.login.domain.dto.LoginRequestDto;

public interface SampleLoginRequestDto {

    default LoginRequestDto sampleLoginRequestDto(String userName, String password) {
        return new LoginRequestDto(userName, password);
    }
}

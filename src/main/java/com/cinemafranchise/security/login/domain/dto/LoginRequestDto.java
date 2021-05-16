package com.cinemafranchise.security.login.domain.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "{userName.not.blank}")
    private final String userName;

    @NotBlank(message = "{password.not.blank}")
    private final String password;
}
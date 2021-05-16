package com.cinemafranchise.security.jwt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@Slf4j
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final String ERROR_UNAUTHORIZED = "Error: Unauthorized";
    private static final String UNAUTHORIZED_ERROR = "Unauthorized error: {}";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error(UNAUTHORIZED_ERROR, authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ERROR_UNAUTHORIZED);
    }
}

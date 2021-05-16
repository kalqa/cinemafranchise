package com.cinemafranchise.security.login;

import com.cinemafranchise.security.login.domain.dto.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface SampleJwtResponse {

    default ResponseEntity<JwtResponse> sampleJwtResponse(String jwtToken, String userName) {
        return ResponseEntity.ok(new JwtResponse(userName, jwtToken));
    }
}

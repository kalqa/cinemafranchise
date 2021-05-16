package com.cinemafranchise.security.login;

import javax.validation.Valid;

import com.cinemafranchise.security.jwt.JwtUtils;
import com.cinemafranchise.security.login.domain.dto.JwtResponse;
import com.cinemafranchise.security.login.domain.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityContextUpdater securityContextUpdater;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        final UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(loginRequest);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        securityContextUpdater.setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final JwtResponse jwtResponse = getJwtResponse(jwt, userDetails);
        return ResponseEntity.ok(jwtResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(LoginRequestDto loginRequest) {
        return new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
    }

    private JwtResponse getJwtResponse(String jwt, UserDetails userDetails) {
        return new JwtResponse(userDetails.getUsername(), jwt);
    }
}

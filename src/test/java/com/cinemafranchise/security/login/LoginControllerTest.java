package com.cinemafranchise.security.login;

import com.cinemafranchise.security.jwt.JwtUtils;
import com.cinemafranchise.security.login.domain.dto.JwtResponse;
import com.cinemafranchise.security.login.domain.dto.LoginRequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginControllerTest implements SampleLoginRequestDto, SampleUser, SampleAuthentication, SampleJwtResponse {

    @Test
    public void should_return_correct_response_with_generated_jwt_token_and_status() {
        // given
        final ResponseEntity<JwtResponse> expectedResponse = sampleJwtResponse("jwtToken", "admin");
        final User principal = sampleUser("admin", "adminpassword");
        final Authentication authentication = sampleAuthentication(principal);

        final AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        final JwtUtils jwtUtils = mock(JwtUtils.class);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("jwtToken");

        final SecurityContextUpdater securityContextUpdater = mock(SecurityContextUpdater.class);
        doNothing().when(securityContextUpdater).setAuthentication(isA(Authentication.class));

        final LoginRequestDto loginRequestDto = sampleLoginRequestDto("user", "password");

        final LoginController loginController = new LoginController(authenticationManager, jwtUtils, securityContextUpdater);
        // when
        final ResponseEntity<JwtResponse> actualResponse = loginController.login(loginRequestDto);

        // then
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(securityContextUpdater, times(1)).setAuthentication(authentication);
    }
}
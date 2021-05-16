package com.cinemafranchise.security.jwt;

import com.cinemafranchise.security.login.NotForProductionUserDetailsService;
import com.cinemafranchise.security.login.NotForProductionUserRepository;
import com.cinemafranchise.security.login.SecurityContextUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class JwtConfig {

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthEntryPoint();
    }

    @Bean
    public JwtUtils jwtUtils(@Value("${auth.movie.jwtSecret:s3ofersaz}") String jwtSecret,
                             @Value("${auth.movie.jwtExpirationMs:86400000}") int jwtExpirationMs) {
        return new JwtUtils(jwtSecret, jwtExpirationMs);
    }

    @Bean
    public JwtParser jwtParser() {
        return new JwtParser();
    }

    @Bean
    public SecurityContextUpdater securityContextUpdater() {
        return new SecurityContextUpdater();
    }

    @Bean
    public JwtAuthTokenFilter authTokenFilter(@Autowired JwtParser jwtParser,
                                              @Autowired JwtUtils jwtUtils,
                                              @Autowired SecurityContextUpdater securityContextUpdater,
                                              @Autowired UserDetailsService userDetailsService) {
        return new JwtAuthTokenFilter(userDetailsService, jwtUtils, jwtParser, securityContextUpdater);
    }

    @Bean
    public UserDetailsService userDetailsService(@Autowired NotForProductionUserRepository repository) {
        return new NotForProductionUserDetailsService(repository);
    }
}

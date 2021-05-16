package com.cinemafranchise.infrastructure.service.movie;

import java.time.Duration;

import com.cinemafranchise.infrastructure.service.movie.client.HttpMovieDetailsClient;
import com.cinemafranchise.infrastructure.service.movie.error.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovieHttpClientConfig {

    @Bean
    RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    RestTemplate restTemplate(@Value("${movie.http.client.config.connectionTimeout}") long connectionTimeout,
                              @Value("${movie.http.client.config.readTimeout}") long readTimeout,
                              RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    RemoteMovieDetailsClient remoteMovieClient(RestTemplate restTemplate,
                                               @Value("${movie.http.client.config.uri:http://example.com}") String uri,
                                               @Value("${movie.http.client.config.port:80}") Integer port,
                                               @Value("${movie.http.client.config.imdb.apikey}") String apiKey) {
        return new HttpMovieDetailsClient(restTemplate, uri, port, apiKey);
    }
}

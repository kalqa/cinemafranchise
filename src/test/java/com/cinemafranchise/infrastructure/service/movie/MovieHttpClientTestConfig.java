package com.cinemafranchise.infrastructure.service.movie;

import org.springframework.web.client.RestTemplate;

public class MovieHttpClientTestConfig extends MovieHttpClientConfig {

    public RemoteMovieDetailsClient remoteMovieTestClient(String uri, int port, int connectionTimeout, int readTimeout, String apikey) {
        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteMovieClient(restTemplate, uri, port, apikey);
    }
}

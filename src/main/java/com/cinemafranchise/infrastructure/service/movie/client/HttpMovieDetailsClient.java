package com.cinemafranchise.infrastructure.service.movie.client;

import com.cinemafranchise.infrastructure.service.movie.RemoteMovieDetailsClient;
import com.cinemafranchise.infrastructure.service.movie.dto.MovieDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
public class HttpMovieDetailsClient implements RemoteMovieDetailsClient {

    private static final String APIKEY = "apikey";
    private static final String MOVIE_ID_QUERY_PARAM_KEY = "i";

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;
    private final String apiKey;

    @Override
    public MovieDetailsDto getDetails(String movieId, String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {
            UriComponentsBuilder builder = buildQueryParamsAndPort(movieId, apiKey, path, port);
            ResponseEntity<MovieDetailsDto> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, MovieDetailsDto.class);
            final MovieDetailsDto body = response.getBody();
            return (body != null) ? body : MovieDetailsDto.empty();
        } catch (ResourceAccessException e) {
            return MovieDetailsDto.empty();
        }
    }

    private UriComponentsBuilder buildQueryParamsAndPort(String movieId, String apiKey, String path, Integer port) {
        return UriComponentsBuilder.fromHttpUrl(uri)
                .port(port)
                .path(path)
                .queryParam(APIKEY, apiKey)
                .queryParam(MOVIE_ID_QUERY_PARAM_KEY, movieId);
    }
}

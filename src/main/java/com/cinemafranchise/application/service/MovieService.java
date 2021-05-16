package com.cinemafranchise.application.service;

import java.util.Collections;

import com.cinemafranchise.application.controller.MovieRequest;
import com.cinemafranchise.application.controller.RateMovieRequest;
import com.cinemafranchise.domain.movie.CreateMovieCommand;
import com.cinemafranchise.domain.movie.RateMovieCommand;
import com.cinemafranchise.infrastructure.service.movie.RemoteMovieDetailsClient;
import com.cinemafranchise.infrastructure.service.movie.dto.MovieDetailsDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

    private final CommandGateway commandGateway;
    private final RemoteMovieDetailsClient client;
    private final FindMovieByTitleService service;

    public void saveMovie(MovieRequest request) {
        commandGateway.send(new CreateMovieCommand(request.getTitle(),
                request.getImdbId(),
                Collections.singletonList(request.getMovieStars())));
    }

    public void saveMovieRating(String title, RateMovieRequest request) {
        commandGateway.send(new RateMovieCommand(title, Collections.singletonList(request.getMovieStars())));
    }

    public MovieDetailsDto fetchMovieDetails(String title) {
        String imdbId = service.findImdbIdByMovieTitle(title);
        if (imdbId.isEmpty()) {
            throw new IllegalStateException();
        }
        return client.getMovieDetailsBy(imdbId);
    }
}

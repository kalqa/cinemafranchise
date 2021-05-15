package com.cinemafranchise.application.service;

import java.util.Collections;

import com.cinemafranchise.application.controller.MovieRequest;
import com.cinemafranchise.application.controller.RateMovieRequest;
import com.cinemafranchise.domain.movie.CreateMovieCommand;
import com.cinemafranchise.domain.movie.RateMovieCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

    private final CommandGateway commandGateway;

    public void saveMovie(MovieRequest request) {
        commandGateway.send(new CreateMovieCommand(request.getTitle(),
                request.getImdbId(),
                Collections.singletonList(request.getMovieStars())));
    }

    public void saveMovieRating(String title, RateMovieRequest request) {
        commandGateway.send(new RateMovieCommand(title, Collections.singletonList(request.getMovieStars())));
    }
}

package com.cinemafranchise.application.service;

import java.util.Collections;

import com.cinemafranchise.application.controller.MovieRequest;
import com.cinemafranchise.domain.movie.RateMovieCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

    private final CommandGateway commandGateway;

    public void saveMovieRating(MovieRequest request) {
        commandGateway.send(new RateMovieCommand(request.getTitle(), Collections.singletonList(request.getMovieStars())));
    }
}

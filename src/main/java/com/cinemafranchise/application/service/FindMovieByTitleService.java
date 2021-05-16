package com.cinemafranchise.application.service;

import java.util.concurrent.ExecutionException;

import com.cinemafranchise.domain.movie.GetMovieQuery;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class FindMovieByTitleService {

    private final QueryGateway queryGateway;

    String findImdbIdByMovieTitle(String title) {
        try {
            return queryGateway.query(new GetMovieQuery(title), String.class).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException();
        }
    }
}

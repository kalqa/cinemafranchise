package com.cinemafranchise.application.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.cinemafranchise.domain.movie.GetMovieQuery;
import com.cinemafranchise.domain.movie.Movie;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class MovieProjector {

    private final Repository<Movie> movieRepository;

    public MovieProjector(Repository<Movie> movieRepository) {
        this.movieRepository = movieRepository;
    }

    @QueryHandler
    public String findImdbIdByMovieTitle(GetMovieQuery query) throws ExecutionException, InterruptedException {
        CompletableFuture<Movie> future = new CompletableFuture<>();
        movieRepository.load("" + query.getMovieTitle()).execute(future::complete);
        return future.get().getImdbId();
    }
}

package com.cinemafranchise.domain.movieshow;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class MovieShowProjector {

    private final Repository<MovieShow> movieShowRepository;

    public MovieShowProjector(Repository<MovieShow> movieShowRepository) {
        this.movieShowRepository = movieShowRepository;
    }

    @QueryHandler
    public MovieShow getMovieShow(GetMovieShowQuery query) throws ExecutionException, InterruptedException {
        CompletableFuture<MovieShow> future = new CompletableFuture<>();
        movieShowRepository.load("" + query.getMovieShowId()).execute(future::complete);
        return future.get();
    }
}

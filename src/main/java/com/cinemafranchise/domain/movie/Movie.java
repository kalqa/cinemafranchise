package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Movie {

    @AggregateIdentifier
    MovieId movieId;
    MovieImdbId movieImdbId;
    MovieTitle movieTitle;
    MovieRating movieRating;

    protected Movie() {
    }
}

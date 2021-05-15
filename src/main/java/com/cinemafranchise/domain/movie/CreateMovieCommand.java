package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import com.cinemafranchise.shared.common.MovieTitle;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateMovieCommand {

    @TargetAggregateIdentifier
    MovieId movieId;
    MovieImdbId movieImdbId;
    MovieTitle movieTitle;
    MovieRating movieRating;
}

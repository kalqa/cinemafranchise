package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import com.cinemafranchise.shared.common.MovieTitle;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateMovieShowCommand {

    @TargetAggregateIdentifier
    MovieTitle movieTitle;
    MovieId movieId;
    Shows shows;
}
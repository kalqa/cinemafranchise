package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateMovieShowCommand {

    @TargetAggregateIdentifier
    MovieShowId movieShowId;
    MovieId movieId;
    Shows shows;
}
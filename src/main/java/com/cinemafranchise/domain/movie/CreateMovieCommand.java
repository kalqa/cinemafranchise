package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateMovieCommand {

    @TargetAggregateIdentifier
    MovieId movieId;
}

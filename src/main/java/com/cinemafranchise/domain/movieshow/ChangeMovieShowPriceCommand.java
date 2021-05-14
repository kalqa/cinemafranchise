package com.cinemafranchise.domain.movieshow;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ChangeMovieShowPriceCommand {

    @TargetAggregateIdentifier
    MovieShowId movieShowId;
}

package com.cinemafranchise.domain.movieshow;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ChangeMovieShowPriceAndTimeCommand {

    @TargetAggregateIdentifier
    MovieShowId movieShowId;
    Shows shows;
}

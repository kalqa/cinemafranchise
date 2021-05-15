package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieTitle;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ChangeMovieShowPriceAndTimeCommand {

    @TargetAggregateIdentifier
    MovieTitle movieTitle;
    Shows shows;
}

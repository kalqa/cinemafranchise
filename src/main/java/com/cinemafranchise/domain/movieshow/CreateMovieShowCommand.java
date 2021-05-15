package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateMovieShowCommand {

    @TargetAggregateIdentifier
    String movieTitle;
    MovieId movieId;
    ZonedDateTime time;
    BigDecimal value;
}
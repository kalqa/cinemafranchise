package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ChangeMovieShowPriceAndTimeCommand {

    @TargetAggregateIdentifier
    String movieTitle;
    ZonedDateTime time;
    BigDecimal value;
}

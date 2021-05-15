package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.Value;

@Value
public class MovieShowPriceAndTimeChangedEvent {

    String movieTitle;
    ZonedDateTime time;
    BigDecimal value;
}
